package mfrf.sunken_world.mixin;

import mfrf.sunken_world.helper.MathHelper;
import mfrf.sunken_world.helper.Tools;
import mfrf.sunken_world.registry.Items;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

@Mixin(HumanoidModel.class)
public abstract class MixinHumanoidModel<T extends LivingEntity> {
    private static final HashMap<UUID, Long> CACHED_TIME = new HashMap<>();

    @Final
    @Shadow
    public ModelPart leftArm;
    @Final
    @Mutable
    public ModelPart rightArm;

    @Shadow
    protected abstract float rotlerpRad(float pAngle, float pMaxAngle, float pMul);


    @Shadow
    public HumanoidModel.ArmPose rightArmPose;

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At(value = "RETURN"), remap = false)
    public void setupHand(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {
        if (pEntity instanceof Player player) {
            if (player.isSwimming() && player.getItemInHand(InteractionHand.MAIN_HAND).is(Items.UNDERWATER_THRUSTER.get())) {
                if (!CACHED_TIME.containsKey(player.getUUID())) {
                    CACHED_TIME.put(player.getUUID(), player.level.getGameTime());
                }
                long swimAmountShift = player.level.getGameTime() - CACHED_TIME.get(player.getUUID());

                float f1 = 1f;
                float f2 = 1f;
                float f6 = 20f / 8f;
//            leftArm.xRot = this.rotlerpRad(f2, this.leftArm.xRot, ((float)Math.PI / 2F) * f6);
//            rightArm.xRot = Mth.lerp(f1, this.rightArm.xRot, ((float)Math.PI / 2F) * f6);

//            leftArm.yRot = this.rotlerpRad(f2, this.leftArm.yRot, (float)Math.PI);
//            rightArm.yRot = Mth.lerp(f1, this.rightArm.yRot, (float)Math.PI);

//                leftArm.zRot = this.rotlerpRad(f2, this.leftArm.zRot, (float) Math.PI);
//                rightArm.yRot = leftArm.yRot = (float) (MathHelper.linearLerpWithMax(swimAmountShift, 0, 10) * Math.PI);
//                leftArm.xRot = rightArm.xRot = leftArm.zRot = rightArm.zRot = rightArm.yRot = leftArm.yRot = (float) Math.PI / 2f;

            } else {
                if (pAgeInTicks == 19) {
                    CACHED_TIME.put(player.getUUID(), player.level.getGameTime());
                }
            }
        }
    }
}
