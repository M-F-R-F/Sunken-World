package mfrf.sunken_world.render.tile_render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import mfrf.sunken_world.blocks.overworld_beacon.WorldBeacon;
import mfrf.sunken_world.blocks.overworld_beacon.TileWorldBeacon;
import mfrf.sunken_world.registry.Blocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class TileOverworldBeaconRenderer implements BlockEntityRenderer<TileWorldBeacon> {

    private final BlockEntityRendererProvider.Context context;

    public TileOverworldBeaconRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }


    @Override
    public void render(TileWorldBeacon pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        BlockState blockState = pBlockEntity.getLevel().getBlockState(pBlockEntity.getBlockPos());
        if (blockState.is(Blocks.WORLD_BEACON.block().get()) && pBlockEntity.getLevel().isLoaded(pBlockEntity.getBlockPos()) && blockState.getValue(WorldBeacon.CHARGE)) {
            long gameTime = pBlockEntity.getLevel().getGameTime();


        pPoseStack.pushPose();
        pPoseStack.translate(0, 1, 0);
        BeaconRenderer.renderBeaconBeam(pPoseStack, pBufferSource, BeaconRenderer.BEAM_LOCATION, pPartialTick, 1, gameTime, 0, 7, new float[]{(float) Math.abs(Math.sin(gameTime / 10f)), 1f, 1f}, 0.15f, 0.18f);
        pPoseStack.popPose();

        pPoseStack.pushPose();
        VertexConsumer buffer = pBufferSource.getBuffer(RenderType.endPortal());

        pPoseStack.translate(-7, 0, -7);
        pPoseStack.translate(0, 7.999, 0);
        pPoseStack.scale(15, 1, 15);

        Matrix4f pPose = pPoseStack.last().pose();

        buffer.vertex(pPose, 0.0F, (float) 0, 0.0F).endVertex();
        buffer.vertex(pPose, 1.0F, (float) 0, 0.0F).endVertex();
        buffer.vertex(pPose, 1.0F, (float) 0, 1.0F).endVertex();
        buffer.vertex(pPose, 0.0F, (float) 0, 1.0F).endVertex();

        buffer.vertex(pPose, 0.0F, (float) 0, 1.0F).endVertex();
        buffer.vertex(pPose, 1.0F, (float) 0, 1.0F).endVertex();
        buffer.vertex(pPose, 1.0F, (float) 0, 0.0F).endVertex();
        buffer.vertex(pPose, 0.0F, (float) 0, 0.0F).endVertex();

        pPoseStack.popPose();

        }
    }


    @Override
    public boolean shouldRenderOffScreen(TileWorldBeacon pBlockEntity) {
        return true;
    }


    @Override
    public int getViewDistance() {
        return 256;
    }
}
