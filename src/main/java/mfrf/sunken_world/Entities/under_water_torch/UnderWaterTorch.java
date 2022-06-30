package mfrf.sunken_world.Entities.under_water_torch;

import mfrf.sunken_world.Entities.water_block_projectile.WaterBlockProjectile;
import mfrf.sunken_world.registry.Entities;
import mfrf.sunken_world.registry.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class UnderWaterTorch extends ThrowableProjectile {
    private ItemStack stack = ItemStack.EMPTY;

    public UnderWaterTorch(EntityType<? extends UnderWaterTorch> p_37466_, Level p_37467_) {
        super(p_37466_, p_37467_);
    }


    public UnderWaterTorch(LivingEntity shooter, Level level) {
        super(Entities.UNDERWATER_TORCH.get(), shooter, level);
    }


    @Override
    protected void defineSynchedData() {
    }

    @Override
    public void tick() {
        super.tick();

        if (!stack.isEmpty() || stack.is(Items.THROWABLE_UNDERWATER_TORCH.get())) {
            if (stack.getDamageValue() >= stack.getMaxDamage()) {
                this.remove(RemovalReason.DISCARDED);
            } else {
                stack.setDamageValue(stack.getMaxDamage() + 1);
            }
        } else {
            this.remove(RemovalReason.DISCARDED);
        }



        level.addParticle(ParticleTypes.SMOKE, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, getX(), getY(), getZ(), 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("torch_instance")) {
            stack = ItemStack.of(pCompound.getCompound("torch_instance"));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.put("torch_instance", stack.serializeNBT());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

}
