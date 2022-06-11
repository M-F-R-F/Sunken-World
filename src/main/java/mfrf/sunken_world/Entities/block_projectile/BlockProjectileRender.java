package mfrf.sunken_world.Entities.block_projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.model.data.EmptyModelData;

import java.util.Optional;

public class BlockProjectileRender extends EntityRenderer<BlockProjectile> {
    public BlockProjectileRender(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(BlockProjectile pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);

        pEntity.getEntityData().get(BlockProjectile.BLOCK_CONTAIN).ifPresent(blockstate -> {
            BlockRenderDispatcher blockRenderer = Minecraft.getInstance().getBlockRenderer();
            pMatrixStack.pushPose();
            blockRenderer.renderSingleBlock(blockstate, pMatrixStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, EmptyModelData.INSTANCE);
            pMatrixStack.popPose();
        });
    }

    @Override
    public ResourceLocation getTextureLocation(BlockProjectile pEntity) {
        return null;
    }
}
