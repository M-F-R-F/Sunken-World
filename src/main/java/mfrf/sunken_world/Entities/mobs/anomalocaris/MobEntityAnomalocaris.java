package mfrf.sunken_world.Entities.mobs.anomalocaris;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.UUID;
import java.util.function.Predicate;

public class MobEntityAnomalocaris extends Monster implements NeutralMob {

    private final WaterBoundPathNavigation waterNavigation;
    @Nullable
    protected RandomSwimmingGoal randomSwimmingGoal;
    @Nullable
    private UUID persistentAngerTarget;
    private int remainingPersistentAngerTime;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    public int moveTime = 0;

    public MobEntityAnomalocaris(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 300;
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new AnomalocarisMoveControl(this);
        this.waterNavigation = new WaterBoundPathNavigation(this, pLevel);
        //todo make it swim
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder mobAttributes = Mob.createMobAttributes().add(Attributes.ATTACK_DAMAGE, 6.5).add(Attributes.ARMOR, 5).add(Attributes.MAX_HEALTH, 50).add(Attributes.MOVEMENT_SPEED, 2.5f);
        return mobAttributes;
    }

    public boolean hunting() {
        return remainingPersistentAngerTime > 0;
    }

    @Override
    public void updateSwimming() {
//        if (!this.level.isClientSide) {
//            if (this.isEffectiveAi() && isInWaterOrBubble()) {
//                this.navigation = waterNavigation;
//                this.setSwimming(true);
//                this.swinging = true;
//            }else {
//                this.setSwimming(false);
//            }
//        }
        super.updateSwimming();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        MoveTowardsRestrictionGoal movetowardsrestrictiongoal = new MoveTowardsRestrictionGoal(this, 1.0D);
        this.randomSwimmingGoal = new RandomSwimmingGoal(this, 1.0D, 40);
        this.goalSelector.addGoal(3, this.randomSwimmingGoal);
        this.randomSwimmingGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

//        this.goalSelector.addGoal(4, new AnomalocarisRangeAttackGoal(this));
        this.goalSelector.addGoal(1, new AnomalocarisMeleeAttackGoal(this, 1.3, false));
        this.goalSelector.addGoal(1, movetowardsrestrictiongoal);
        movetowardsrestrictiongoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, MobEntityAnomalocaris.class, 12.0F, 0.01F));
        this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal<>(this, false));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));

    }

    @Override
    public void tick() {
        super.tick();
        if (getDeltaMovement().length() > 0.016) {
            moveTime++;
        } else {
            moveTime = Math.max(0, moveTime - 1);
        }
    }

    @Override
    public PathNavigation getNavigation() {
        return waterNavigation;
    }

    @Override
    public void aiStep() {
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel) this.level, true);
        }

        if (this.isInWaterOrBubble()) {
            this.setAirSupply(300);
            setNoGravity(true);
        } else if (this.onGround) {
            this.setNoGravity(false);
            this.hasImpulse = true;
            setNoAi(true);
            this.setAirSupply(Math.max(0, this.decreaseAirSupply(4)));
        }

        if (hunting()) {
//            navigation.moveTo(getTarget(), 1.5);
            LivingEntity target = getTarget();
            moveControl.setWantedPosition(target.getX(),target.getY(),target.getZ(),1.5f);
        }

        this.jumping = false;

        super.aiStep();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean hurt = super.hurt(pSource, pAmount);
        Entity entity = pSource.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            startPersistentAngerTimer();
            setTarget(livingEntity);
        }
        return hurt;
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
        private final MobEntityAnomalocaris anomalocaris;

        public AnomalocarisMoveControl(MobEntityAnomalocaris pMob) {
            super(pMob);
            this.anomalocaris = pMob;
        }

        @Override
        public void tick() {
            if (this.operation == Operation.MOVE_TO && !this.anomalocaris.getNavigation().isDone()) {
                Vec3 vec3ToDirection = new Vec3(this.wantedX - this.anomalocaris.getX(), this.wantedY - this.anomalocaris.getY(), this.wantedZ - this.anomalocaris.getZ());
                double lengthTargetVec = vec3ToDirection.length();
                double normalizedTargetVecX = vec3ToDirection.x / lengthTargetVec;
                double normalizedTargetVecY = vec3ToDirection.y / lengthTargetVec;
                double normalizedTargetVecZ = vec3ToDirection.z / lengthTargetVec;

                float targetYAngle = (float) (Mth.atan2(vec3ToDirection.z, vec3ToDirection.x) * (double) (180F / (float) Math.PI)) - 90.0F;
                this.anomalocaris.setYRot(this.rotlerp(this.anomalocaris.getYRot(), targetYAngle, 90.0F));
                this.anomalocaris.yBodyRot = this.anomalocaris.getYRot();

                float actualSpeed = (float) (this.speedModifier * this.anomalocaris.getAttributeValue(Attributes.MOVEMENT_SPEED));
                float speedDelta = Mth.lerp(0.3F, this.anomalocaris.getSpeed(), actualSpeed);
                this.anomalocaris.setSpeed(speedDelta);

                double speedScaleXZ = Math.sin((double) (this.anomalocaris.tickCount + this.anomalocaris.getId()) * 0.5D) * 0.05D;
//                double speedScaleXZ = Math.sin((double) (this.anomalocaris.tickCount + this.anomalocaris.getId()) * 0.5D) * speedDelta;
                double speedX = Math.cos((double) (this.anomalocaris.getYRot() * ((float) Math.PI / 180F)));
                double littleScaleY = Math.sin((double) (this.anomalocaris.tickCount + this.anomalocaris.getId()) * 0.75D) * 0.05D;
//                double littleScaleY = Math.sin((double) (this.anomalocaris.tickCount + this.anomalocaris.getId()) * 0.75D) * speedDelta;
                double speedZ = Math.sin((double) (this.anomalocaris.getYRot() * ((float) Math.PI / 180F)));

                this.anomalocaris.setDeltaMovement(this.anomalocaris.getDeltaMovement().add(speedScaleXZ * speedX, littleScaleY * (speedZ + speedX) * 0.25D + (double) speedDelta * normalizedTargetVecY * 0.1D, speedScaleXZ * speedZ));

                LookControl lookcontrol = this.anomalocaris.getLookControl();
                double shouldLookAtX = this.anomalocaris.getX() + normalizedTargetVecX * 2.0D;
                double shouldLookAtY = this.anomalocaris.getEyeY() + normalizedTargetVecY / lengthTargetVec;
                double shouleLookAtZ = this.anomalocaris.getZ() + normalizedTargetVecZ * 2.0D;

                double lookWantedX = lookcontrol.getWantedX();
                double lookWantedY = lookcontrol.getWantedY();
                double lookWantedZ = lookcontrol.getWantedZ();
                if (!lookcontrol.isLookingAtTarget()) {
                    lookWantedX = shouldLookAtX;
                    lookWantedY = shouldLookAtY;
                    lookWantedZ = shouleLookAtZ;
                }

                this.anomalocaris.getLookControl().setLookAt(Mth.lerp(0.125D, lookWantedX, shouldLookAtX), Mth.lerp(0.125D, lookWantedY, shouldLookAtY), Mth.lerp(0.125D, lookWantedZ, shouleLookAtZ), 10.0F, 40.0F);
//                this.anomalocaris.setMoving(true);
            } else {
                this.anomalocaris.setSpeed(0.0F);
//                this.anomalocaris.setMoving(false);
            }
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

    static class AnomalocarisMeleeAttackGoal extends MeleeAttackGoal {

        public AnomalocarisMeleeAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        }

        @Override
        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return 9f;
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
