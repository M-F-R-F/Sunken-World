package mfrf.sunken_world.blocks.overworld_beacon;

import mfrf.sunken_world.helper.CurioHelper;
import mfrf.sunken_world.helper.Tools;
import mfrf.sunken_world.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.List;
import java.util.function.Predicate;

public class TileWorldBeacon extends BlockEntity {
    public TileWorldBeacon(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntities.WORLD_BEACON_TILE.get(), pWorldPosition, pBlockState);
    }


    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, TileWorldBeacon pBlockEntity) {
        if (pLevel.getServer().getLevel(Dimensions.SUNKEN_WORLD) == pLevel) {
            searchAndTeleport(pLevel, pPos, Level.OVERWORLD, itemStack -> itemStack.getItem() == Items.GRACE_OF_OCEAN_PENDANT.get());
        } else if (pLevel.getServer().getLevel(Level.OVERWORLD) == pLevel) {
            searchAndTeleport(pLevel, pPos, Dimensions.SUNKEN_WORLD, itemStack -> itemStack.getItem() == Items.GRACE_OF_OCEAN_PENDANT.get());
        }

        //TODO check multiblock structure
    }

    private static void searchAndTeleport(Level pLevel, BlockPos pPos, ResourceKey<Level> dimension, Predicate<ItemStack> predicate) {
        List<Entity> entities = pLevel.getEntities(null, AABB.ofSize(new Vec3(pPos.getX(), pPos.getY(), pPos.getZ()), 16, 16, 16));
        boolean flag = pLevel.getBlockState(pPos).getValue(WorldBeacon.CHARGE);
        boolean b = entities.stream().anyMatch(entity -> {
            if (entity instanceof LivingEntity livingEntity) {
                return !CurioHelper.findAnyItem(livingEntity, SlotTypePreset.NECKLACE.getIdentifier(), predicate).isEmpty();
            }
            return false;
        });
        if (b && !flag) {
            pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(WorldBeacon.CHARGE, true));
            pLevel.playSound(null, pPos, SoundEvents.TRIDENT_THUNDER, SoundSource.AMBIENT, 1.0F, 1.0F);
//            pLevel.playSound(null, pPos, SoundEvents.PORTAL_TRIGGER, SoundSource.AMBIENT, 1.0F, 1.0F);
        }

        if (!b && flag) {
            pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(WorldBeacon.CHARGE, false));
        }

        //todo add some trick to connect player


        if (flag) {
            List<Entity> enteredPortal = pLevel.getEntities(null, AABB.ofSize(new Vec3(pPos.getX(), pPos.getY() + 8, pPos.getZ()), 15, 1, 15));
            enteredPortal.stream().filter(entity -> entity instanceof LivingEntity).map(entity -> (LivingEntity) entity).forEach(entity -> {
                Tools.teleport(entity, entity.getServer().getLevel(dimension), pPos, true);
            });
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}
