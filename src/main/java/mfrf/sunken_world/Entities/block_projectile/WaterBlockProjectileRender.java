package mfrf.sunken_world.Entities.block_projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraftforge.client.model.data.EmptyModelData;

public class WaterBlockProjectileRender extends EntityRenderer<WaterBlockProjectile> {
    public WaterBlockProjectileRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(WaterBlockProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);

            BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            pMatrixStack.pushPose();
            blockRenderer.renderSingleBlock(Blocks.WATER.defaultBlockState().setValue(LiquidBlock.LEVEL,0), pMatrixStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
            pMatrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(WaterBlockProjectile pEntity) {
        return null;
    }
}
