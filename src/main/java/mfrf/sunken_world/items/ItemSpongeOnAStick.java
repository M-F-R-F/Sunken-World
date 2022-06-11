package mfrf.sunken_world.items;

import com.google.common.collect.Lists;
import mfrf.sunken_world.Config;
import mfrf.sunken_world.Entities.block_projectile.BlockProjectile;
import mfrf.sunken_world.registry.Entities;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.Queue;

import static net.minecraft.world.level.block.Block.dropResources;

public class ItemSpongeOnAStick extends Item {
    public ItemSpongeOnAStick(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand pUsedHand) {
        if (!level.isClientSide()) {
            BlockPos blockPos = player.blockPosition();
            ItemStack itemInHand = player.getItemInHand(pUsedHand);
            CompoundTag orCreateTag = itemInHand.getOrCreateTag();
            Integer capacity = Config.MAX_WATER_CONTAINS_IN_SPONGE_STICK.get();
            if (!orCreateTag.contains("water")) {
                orCreateTag.putInt("water", removeWaterBreadthFirstSearch(level, blockPos, capacity));
                return InteractionResultHolder.success(itemInHand);
            } else {
                int water = orCreateTag.getInt("water");
                int capRemain = capacity - water;

                if (player.isShiftKeyDown()) {
                    Entity spawn = Entities.BLOCK_PROJECTILE.get().spawn(((ServerLevel) level), null, player, player.getOnPos(), MobSpawnType.TRIGGERED, true, false);
                    Vec3 lookAngle = player.getLookAngle();
                    ((BlockProjectile) spawn).shoot(lookAngle.x, lookAngle.y, lookAngle.z, 4f, 0);
                    orCreateTag.putInt("water", water - 1);

                    return InteractionResultHolder.success(itemInHand);
                } else if (capRemain > 0) {
                    orCreateTag.putInt("water", water + removeWaterBreadthFirstSearch(level, blockPos, capRemain));

                    return InteractionResultHolder.success(itemInHand);
                }
            }
        }
        return super.use(level, player, pUsedHand);
    }


    private int removeWaterBreadthFirstSearch(Level pLevel, BlockPos pPos, int max) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(pPos, 0));
        int i = 0;

        while (!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos blockpos = tuple.getA();
            int j = tuple.getB();

            for (Direction direction : Direction.values()) {
                BlockPos blockpos1 = blockpos.relative(direction);
                BlockState blockstate = pLevel.getBlockState(blockpos1);
                FluidState fluidstate = pLevel.getFluidState(blockpos1);
                Material material = blockstate.getMaterial();
                if (fluidstate.is(FluidTags.WATER)) {
                    if (blockstate.getBlock() instanceof BucketPickup && !((BucketPickup) blockstate.getBlock()).pickupBlock(pLevel, blockpos1, blockstate).isEmpty()) {
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (blockstate.getBlock() instanceof LiquidBlock) {
                        pLevel.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    } else if (material == Material.WATER_PLANT || material == Material.REPLACEABLE_WATER_PLANT) {
                        BlockEntity blockentity = blockstate.hasBlockEntity() ? pLevel.getBlockEntity(blockpos1) : null;
                        dropResources(blockstate, pLevel, blockpos1, blockentity);
                        pLevel.setBlock(blockpos1, Blocks.AIR.defaultBlockState(), 3);
                        ++i;
                        if (j < 6) {
                            queue.add(new Tuple<>(blockpos1, j + 1));
                        }
                    }
                }
            }

            if (i > max) {
                break;
            }
        }

        return i;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        CompoundTag orCreateTag = pStack.getOrCreateTag();
        if(!orCreateTag.contains("water")){
            orCreateTag.putInt("water", 0);
        }
        return (int) (13 * ((float) orCreateTag.getInt("water")) / Config.MAX_WATER_CONTAINS_IN_SPONGE_STICK.get());
    }

}
