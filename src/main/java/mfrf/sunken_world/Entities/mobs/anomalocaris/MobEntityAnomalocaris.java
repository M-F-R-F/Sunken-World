package mfrf.sunken_world.Entities.mobs.anomalocaris;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.function.Predicate;

public class MobEntityAnomalocaris extends Monster {

    @Nullable
    protected RandomStrollGoal randomStrollGoal;

    protected MobEntityAnomalocaris(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 300;
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new AnomalocarisMoveControl(this);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        MoveTowardsRestrictionGoal movetowardsrestrictiongoal = new MoveTowardsRestrictionGoal(this, 1.0D);
        this.randomStrollGoal = new RandomStrollGoal(this, 1.0D, 80);
        this.goalSelector.addGoal(4, new AnomalocarisAttackGoal(this));
        this.goalSelector.addGoal(5, movetowardsrestrictiongoal);
        this.goalSelector.addGoal(7, this.randomStrollGoal);
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, MobEntityAnomalocaris.class, 12.0F, 0.01F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
        this.randomStrollGoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        movetowardsrestrictiongoal.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, new AnomalocarisAttackSelector(this)));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void aiStep() {
        super.aiStep();
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

    static class AnomalocarisAttackGoal extends Goal {
        public AnomalocarisAttackGoal(MobEntityAnomalocaris mobEntityAnomalocaris) {
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
