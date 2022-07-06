package mfrf.sunken_world.Entities.mobs.piranha;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.TropicalFishModelA;
import net.minecraft.client.model.geom.ModelPart;

public class PiranhaModel extends TropicalFishModelA<MobEntityPiranha> {

    public PiranhaModel(ModelPart pRoot) {
        super(pRoot);
    }

    @Override
    public void setupAnim(MobEntityPiranha pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {

    }
}
