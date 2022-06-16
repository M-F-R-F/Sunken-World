package mfrf.sunken_world.Entities.water_block_projectile;

import mfrf.sunken_world.registry.Entities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

public class WaterBlockProjectile extends ThrowableProjectile {
    public static final EntityDataAccessor<Integer> LIFETIME = SynchedEntityData.defineId(WaterBlockProjectile.class, EntityDataSerializers.INT);

    public WaterBlockProjectile(EntityType<? extends WaterBlockProjectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    public WaterBlockProjectile(LivingEntity shooter, Level level) {
        super(Entities.WATER_BLOCK_PROJECTILE.get(), shooter, level);
    }


    @Override
    protected void defineSynchedData() {
        this.entityData.define(LIFETIME, 0);
    }

    @Override
    public void tick() {
        super.tick();
        int lifetime = entityData.get(LIFETIME);
        if (lifetime > 800) {
            tryPlaceWater(blockPosition());
        }
        entityData.set(LIFETIME, lifetime + 1);
//        this.move(MoverType.SELF, getDeltaMovement());
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        BlockPos blockPos = blockHitResult.getBlockPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (!(blockState.is(Blocks.WATER) && blockState.getValue(LiquidBlock.LEVEL) == 0)) {
            tryPlaceWater(blockPos);
        }
    }

    private void tryPlaceWater(BlockPos blockPos) {
        for (Direction value : Direction.values()) {
            BlockPos relative = blockPos.relative(value);
            if (level.getBlockState(relative).canBeReplaced(Fluids.WATER)) {
                level.setBlockAndUpdate(relative, Blocks.WATER.defaultBlockState());
                break;
            }
        }
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected float getGravity() {
        return 0.01F;
    }


}
