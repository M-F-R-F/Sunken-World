package mfrf.sunken_world.Entities.technical.under_water_torch;

import mfrf.sunken_world.registry.Entities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;

public class UnderWaterTorch extends ThrowableProjectile {

    public static final EntityDataAccessor<Integer> TIME = SynchedEntityData.defineId(UnderWaterTorch.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<ItemStack> STACK = SynchedEntityData.defineId(UnderWaterTorch.class, EntityDataSerializers.ITEM_STACK);

    public ItemStack getStack() {
        return this.entityData.get(STACK);
    }

    public UnderWaterTorch(EntityType<? extends UnderWaterTorch> p_37466_, Level p_37467_) {
        super(p_37466_, p_37467_);
    }


    public UnderWaterTorch(LivingEntity shooter, Level level) {
        super(Entities.UNDERWATER_TORCH.get(), shooter, level);
    }


    @Override
    protected void defineSynchedData() {
        this.entityData.define(STACK, ItemStack.EMPTY);
        this.entityData.define(TIME, -1);
    }

    @Override
    public void tick() {
        int time = getTime();

        if (time < 0) {
            this.remove(RemovalReason.DISCARDED);
        }
        decreaseTime();


        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level.getBlockState(blockpos);
        VoxelShape voxelshape = blockstate.getCollisionShape(this.level, blockpos);
        boolean flag = true;
        if (!voxelshape.isEmpty()) {
            Vec3 pos = this.position();

            for (AABB aabb : voxelshape.toAabbs()) {
                if (aabb.move(blockpos).contains(pos)) {
                    this.setDeltaMovement(Vec3.ZERO);
                    flag = false;
                    break;
                }
            }
        }

        if (flag) {
            super.tick();
        }

        level.addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        if(random.nextInt(20) < 3) {
            level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX(), getY() + 0.25, getZ(), 0.0D, 0.025D, 0.0D);
        }
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    public boolean isStopped() {
        Vec3 deltaMovement = getDeltaMovement();
        return deltaMovement.y == deltaMovement.x && deltaMovement.x == deltaMovement.z && deltaMovement.z == 0;
    }

    public int getTime() {
        int integer = entityData.get(TIME);
        if (integer == -1) {
            ItemStack stack = entityData.get(STACK);
            integer = stack.getMaxDamage() - stack.getDamageValue();
            entityData.set(TIME, integer);
        }
        return integer;
    }

    public void decreaseTime() {
        entityData.set(TIME, entityData.get(TIME) - 1);
    }
}
