package mfrf.sunken_world.Entities.mobs.anomalocaris;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.eventbus.api.BusBuilder;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;

public class MobEntityAnomalocaris extends Monster implements NeutralMob {

    @Nullable
    protected RandomStrollGoal randomStrollGoal;
    @Nullable
    private UUID persistentAngerTarget;
    private int remainingPersistentAngerTime;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);

    public MobEntityAnomalocaris(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 300;
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new AnomalocarisMoveControl(this);
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createMobAttributes();
    }

    @Override
    public void updateSwimming() {
        if (!this.level.isClientSide) {
            if (this.isEffectiveAi() && isInWaterOrBubble()) {
//                this.navigation = ;
                this.setSwimming(true);
            }
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        MoveTowardsRestrictionGoal movetowardsrestrictiongoal = new MoveTowardsRestrictionGoal(this, 1.0D);
        this.randomStrollGoal = new RandomStrollGoal(this, 1.0D, 80);
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));

        this.goalSelector.addGoal(4, new AnomalocarisRangeAttackGoal(this));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.3, false));
        this.goalSelector.addGoal(5, movetowardsrestrictiongoal);
        movetowardsrestrictiongoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, MobEntityAnomalocaris.class, 12.0F, 0.01F));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));

    }

    @Override
    public void aiStep() {
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }

        if (this.isInWaterOrBubble()) {
            this.setAirSupply(300);

        } else if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F), 0.5D, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.4F)));
            this.setYRot(this.random.nextFloat() * 360.0F);
            this.onGround = false;
            this.hasImpulse = true;
        }

        super.aiStep();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@org.jetbrains.annotations.Nullable UUID pPersistentAngerTarget) {
        this.persistentAngerTarget = pPersistentAngerTarget;
    }

    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public void setRemainingPersistentAngerTime(int pTime) {
        this.remainingPersistentAngerTime = pTime;
    }

    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    static class AnomalocarisMoveControl extends MoveControl {

        public AnomalocarisMoveControl(Mob pMob) {
            super(pMob);
        }

        @Override
        public void tick() {
            super.tick();
        }

    }

    static class AnomalocarisRangeAttackGoal extends Goal {
        public AnomalocarisRangeAttackGoal(MobEntityAnomalocaris mobEntityAnomalocaris) {
        }

        @Override
        public boolean canUse() {
            return false;
        }
    }

    static class AnomalocarisAttackSelector implements Predicate<LivingEntity> {
        //todo become non aggressive
        private final MobEntityAnomalocaris anomalocaris;

        public AnomalocarisAttackSelector(MobEntityAnomalocaris anomalocaris) {
            this.anomalocaris = anomalocaris;
        }

        public boolean test(@Nullable LivingEntity p_32881_) {
            return (p_32881_ instanceof Player || p_32881_ instanceof Squid || p_32881_ instanceof Axolotl) && p_32881_.distanceToSqr(this.anomalocaris) > 9.0D;
        }
    }

}
