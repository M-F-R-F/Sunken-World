package mfrf.sunken_world.Entities.mobs.piranha;

import mfrf.sunken_world.registry.Entities;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * todo fix bug
 *  seek @Guardian
 */
public class MobEntityPiranha extends Monster {
    public static EntityDataAccessor<Integer> HUNGER = SynchedEntityData.defineId(MobEntityPiranha.class, EntityDataSerializers.INT);
    public static EntityDataAccessor<Boolean> HUNTING = SynchedEntityData.defineId(MobEntityPiranha.class, EntityDataSerializers.BOOLEAN);

    public MobEntityPiranha(EntityType<? extends Monster> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(HUNGER, 1200);
        entityData.define(HUNTING, false);
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(MobEntityPiranha.class));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, livingEntity -> entityData.get(HUNTING) && livingEntity.getType() != Entities.MOB_ENTITY_PIRANHA.get()));
    }


    //todo registry
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 35.0D).add(Attributes.MOVEMENT_SPEED, 2.0F).add(Attributes.ATTACK_DAMAGE, 3.0D);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (pKey == HUNGER && entityData.get(HUNGER) <= 0) {
            entityData.set(HUNTING, true);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide()) {
            if (entityData.get(HUNTING)) {
                entityData.set(HUNGER, entityData.get(HUNGER) - 1);
            }
        }
    }

    @Override
    public void killed(ServerLevel pLevel, LivingEntity pKilledEntity) {
        super.killed(pLevel, pKilledEntity);
        entityData.set(HUNGER, (int) pKilledEntity.getMaxHealth() * 120);
    }
}
