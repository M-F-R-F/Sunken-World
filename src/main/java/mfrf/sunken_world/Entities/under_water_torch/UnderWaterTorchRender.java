package mfrf.sunken_world.Entities.under_water_torch;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import mfrf.sunken_world.SunkenWorld;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class UnderWaterTorchRender extends EntityRenderer<UnderWaterTorch> {
    private final ItemRenderer itemRenderer;

    public UnderWaterTorchRender(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    public void render(UnderWaterTorch pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        ItemStack stack = pEntity.getStack();
        if (!stack.isEmpty()) {
            BakedModel bakedmodel = this.itemRenderer.getModel(stack, pEntity.level, (LivingEntity)null, pEntity.getId());
            if (!pEntity.isStopped()) {
                pMatrixStack.mulPose(Quaternion.fromXYZ((float) Math.sin(pEntity.getTime() + pPartialTicks), 0, (float) Math.cos(pEntity.getTime() + pPartialTicks)));
            }
            itemRenderer.render(stack, ItemTransforms.TransformType.GROUND, false, pMatrixStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, bakedmodel);
        }
        pMatrixStack.popPose();
    }


    @Override
    public ResourceLocation getTextureLocation(UnderWaterTorch pEntity) {
        return new ResourceLocation(SunkenWorld.MODID, "textures/item/throwable_underwater_torch");
    }
}
