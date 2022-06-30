package mfrf.sunken_world.Entities.water_block_projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Random;

public class WaterBlockProjectileRender extends EntityRenderer<WaterBlockProjectile> {
    public WaterBlockProjectileRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(WaterBlockProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);

        BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
//        BlockState blockState = Blocks.WATER.defaultBlockState();
        BlockState blockState = Blocks.ICE.defaultBlockState();

        pMatrixStack.pushPose();
        pMatrixStack.translate(-0.5D, 0.0D, -0.5D);
//        blockRenderer.getModelRenderer().tesselateBlock(pEntity.level, blockRenderer.getBlockModel(blockState), blockState, pEntity.blockPosition(), pMatrixStack, pBuffer.getBuffer(RenderType.translucentMovingBlock()), false, new Random(), blockState.getSeed(pEntity.blockPosition()), OverlayTexture.NO_OVERLAY);
        //todo fixit

        pMatrixStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(WaterBlockProjectile pEntity) {
        return null;
    }
}
