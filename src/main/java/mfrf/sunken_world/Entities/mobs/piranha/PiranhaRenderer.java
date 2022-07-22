package mfrf.sunken_world.Entities.mobs.piranha;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class PiranhaRenderer extends MobRenderer<MobEntityPiranha,PiranhaModel> {
    private static final ResourceLocation TEMP_LOCATION = new ResourceLocation("textures/entity/fish/cod.png");

    public PiranhaRenderer(EntityRendererProvider.Context context) {
        super(context, new PiranhaModel(context.bakeLayer(PiranhaModel.PIRANHA_LAYER)), 1f);
    }


    @Override
    public ResourceLocation getTextureLocation(MobEntityPiranha pEntity) {
        return TEMP_LOCATION;
    }

    @Override
    protected void setupRotations(MobEntityPiranha pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {

        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);
        float f = 4.3F * Mth.sin(0.6F * pAgeInTicks);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!pEntityLiving.isInWater()) {
            pMatrixStack.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}
