package mfrf.sunken_world.Entities.block_projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Optional;

public class BlockProjectile extends Projectile {
    public static final EntityDataAccessor<Optional<BlockState>> BLOCK_CONTAIN = SynchedEntityData.defineId(BlockProjectile.class, EntityDataSerializers.BLOCK_STATE);

    public BlockProjectile(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(BLOCK_CONTAIN, Optional.empty());
    }

    @Override
    public void tick() {
        super.tick();
        this.moveTo(getDeltaMovement());
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        Optional<BlockState> blockState = this.entityData.get(BLOCK_CONTAIN);
        blockState.ifPresent(block -> {
            switch (pResult.getType()) {
                case BLOCK -> {
                    BlockHitResult blockHitResult = (BlockHitResult) pResult;
                    BlockPos blockPos = blockHitResult.getBlockPos();

                    while (!level.getBlockState(blockPos).getMaterial().isReplaceable() || blockHitResult.isInside()) {
                        blockPos = blockPos.relative(blockHitResult.getDirection().getOpposite());
                    }
                    level.setBlockAndUpdate(blockPos, block);

                    this.remove(RemovalReason.DISCARDED);
                }
                case ENTITY -> {
                    EntityHitResult entityHitResult = (EntityHitResult) pResult;
                    if (entityHitResult.getEntity() == getOwner()) {
                        BlockPos blockPos = new BlockPos(pResult.getLocation());

                        if (level.getBlockState(blockPos).getMaterial().isReplaceable()) {
                            level.setBlockAndUpdate(blockPos, block);
                        }

                        this.remove(RemovalReason.DISCARDED);
                    }
                }
                case MISS -> {
                    this.remove(RemovalReason.DISCARDED);
                }
            }
        });

    }
}
