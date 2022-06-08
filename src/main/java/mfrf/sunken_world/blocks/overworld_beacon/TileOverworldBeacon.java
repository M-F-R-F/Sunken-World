package mfrf.sunken_world.blocks.overworld_beacon;

import mfrf.sunken_world.helper.CurioHelper;
import mfrf.sunken_world.helper.Tools;
import mfrf.sunken_world.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypePreset;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class TileOverworldBeacon extends BlockEntity {
    public TileOverworldBeacon(BlockPos pWorldPosition, BlockState pBlockState) {
        super(BlockEntities.OVERWORLD_BEACON_TILE.get(), pWorldPosition, pBlockState);
    }


    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, TileOverworldBeacon pBlockEntity) {
        if (pLevel.getServer().getLevel(Dimensions.SUNKEN_WORLD) == pLevel) {
            List<Entity> entities = pLevel.getEntities(null, AABB.ofSize(new Vec3(pPos.getX(), pPos.getY(), pPos.getZ()), 16, 16, 16));
            boolean flag = pLevel.getBlockState(pPos).getValue(OverworldBeacon.CHARGE);
                boolean b = entities.stream().anyMatch(entity -> {
                    if (entity instanceof LivingEntity livingEntity) {
                        return !CurioHelper.findAnyItem(livingEntity, SlotTypePreset.NECKLACE.getIdentifier(), itemStack -> itemStack.getItem() == Items.GRACE_OF_OCEAN_PENDANT.get()).isEmpty();
                    }
                    return false;
                });
                if (b && !flag) {
                    pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(OverworldBeacon.CHARGE, true));
                }

                if (!b && flag) {
                    pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(OverworldBeacon.CHARGE, false));
                }

                //todo add some trick to connect player


                List<Entity> enteredPortal = pLevel.getEntities(null, AABB.ofSize(new Vec3(pPos.getX(), pPos.getY() + 8, pPos.getZ()), 8, 1, 8));
                enteredPortal.stream().filter(entity -> entity instanceof LivingEntity).map(entity -> (LivingEntity) entity).forEach(entity -> {
                    Tools.teleport(entity, entity.getServer().getLevel(Level.OVERWORLD), pPos, true);
                });
        }
    }

    @Override
    public AABB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }
}
