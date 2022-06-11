package mfrf.sunken_world.Entities.bubble;

import mfrf.sunken_world.Config;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

public class BubbleEntity extends Projectile {
    public static final EntityDataAccessor<Integer> OXYGEN_CONTAIN = SynchedEntityData.defineId(BubbleEntity.class, EntityDataSerializers.INT);

    public BubbleEntity(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(OXYGEN_CONTAIN, 0);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (pResult.getEntity() instanceof LivingEntity entity) {
            entity.setAirSupply(entity.getAirSupply() + this.entityData.get(OXYGEN_CONTAIN));
        }
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.isInWater()) {
            this.remove(RemovalReason.DISCARDED);
        }
        this.move(MoverType.SELF, new Vec3(0, Config.OXYGEN_BUBBLE_SPEED.get(), 0));
        this.level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE_COLUMN_UP, this.getX(), this.getY(), this.getZ(), 0, Config.OXYGEN_BUBBLE_SPEED.get(), 0);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
