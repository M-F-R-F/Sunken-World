package mfrf.sunken_world.Entities;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.registry.Attributes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BubbleEntity extends Projectile {
    private static final EntityDataAccessor<Integer> OXYGEN_CONTAIN = SynchedEntityData.defineId(BubbleEntity.class, EntityDataSerializers.INT);
    //todo registry

    protected BubbleEntity(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
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
    }

    @Override
    protected void onHitBlock(BlockHitResult p_37258_) {
        this.remove(RemovalReason.DISCARDED);
    }

    @Override
    public void tick() {
        super.tick();
        this.move(MoverType.SELF, new Vec3(0, Config.OXYGEN_BUBBLE_SPEED.get(), 0));
    }
}
