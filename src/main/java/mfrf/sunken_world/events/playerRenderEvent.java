//package mfrf.sunken_world.events;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.math.Vector3f;
//import mfrf.sunken_world.SunkenWorld;
//import mfrf.sunken_world.registry.Items;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.player.AbstractClientPlayer;
//import net.minecraft.client.player.LocalPlayer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.client.renderer.entity.player.PlayerRenderer;
//import net.minecraft.util.Mth;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.entity.HumanoidArm;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.RenderHandEvent;
//import net.minecraftforge.client.event.RenderItemInFrameEvent;
//import net.minecraftforge.client.event.RenderPlayerEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//
//@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
//public class playerRenderEvent {
//
//
//    @SubscribeEvent
//    public static void onPreRender(RenderPlayerEvent.Pre event){
//        Player player = event.getPlayer();
//        if(player.isSwimming()){
//            event.setCanceled(true);
//        }
//    }
//
//    @SubscribeEvent
//    public static void onPostRender(RenderPlayerEvent.Post event) {
//        Player player = event.getPlayer();
//        ItemStack itemInHand = player.getItemInHand(InteractionHand.MAIN_HAND);
//        if (itemInHand.is(Items.UNDERWATER_THRUSTER.get())) {
//            PlayerRenderer renderer = event.getRenderer();
//            PoseStack poseStack = event.getPoseStack();
//            float partialTick = event.getPartialTick();
//            float attackAnim = player.getAttackAnim(partialTick);
//            float pitch = Mth.lerp(partialTick, player.xRotO, player.getXRot());
//            MultiBufferSource pBuffer = event.getMultiBufferSource();
////            float equippedProgress = 1.0F - Mth.lerp(partialTick, this.oMainHandHeight, this.mainHandHeight);
//            float equippedProgress = 1.0F;
//            int pCombinedLight = event.getPackedLight();
////            this.minecraft.getEntityRenderDispatcher().getPackedLightCoords(this.minecraft.player, pPartialTicks)
//
//            poseStack.pushPose();
//            float f = Mth.sqrt(attackAnim);
//            float f1 = -0.2F * Mth.sin(attackAnim * (float) Math.PI);
//            float f2 = -0.4F * Mth.sin(f * (float) Math.PI);
//            poseStack.translate(0.0D, (double) (-f1 / 2.0F), (double) f2);
//            float f3 = calculateMapTilt(pitch);
//            poseStack.translate(0.0D, (double) (0.04F + equippedProgress * -1.2F + f3 * -0.5F), (double) -0.72F);
//            poseStack.mulPose(Vector3f.XP.rotationDegrees(f3 * -85.0F));
//            if (!player.isInvisible()) {
//                poseStack.pushPose();
//                poseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
//                renderMapHand(poseStack, pBuffer, pCombinedLight, HumanoidArm.RIGHT, renderer);
//                renderMapHand(poseStack, pBuffer, pCombinedLight, HumanoidArm.LEFT, renderer);
//                poseStack.popPose();
//            }
//
////            float f4 = Mth.sin(f * (float) Math.PI);
////            poseStack.mulPose(Vector3f.XP.rotationDegrees(f4 * 20.0F));
////            poseStack.scale(2.0F, 2.0F, 2.0F);
////            renderMap(poseStack, pBuffer, pCombinedLight, this.mainHandItem);
//            poseStack.popPose();
//        }
//    }
//
//
//    private static float calculateMapTilt(float pPitch) {
//        float f = 1.0F - pPitch / 45.0F + 0.1F;
//        f = Mth.clamp(f, 0.0F, 1.0F);
//        return -Mth.cos(f * (float) Math.PI) * 0.5F + 0.5F;
//    }
//
//
//    private static void renderMapHand(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pCombinedLight, HumanoidArm pSide, PlayerRenderer playerrenderer) {
//        LocalPlayer player = Minecraft.getInstance().player;
//        RenderSystem.setShaderTexture(0, player.getSkinTextureLocation());
////        PlayerRenderer playerrenderer = Minecraft.getInstance().getEntityRenderDispatcher().<AbstractClientPlayer>getRenderer(player);
//        pMatrixStack.pushPose();
//        float f = pSide == HumanoidArm.RIGHT ? 1.0F : -1.0F;
//        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(92.0F));
//        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(45.0F));
//        pMatrixStack.mulPose(Vector3f.ZP.rotationDegrees(f * -41.0F));
//        pMatrixStack.translate((double) (f * 0.3F), (double) -1.1F, (double) 0.45F);
//        if (pSide == HumanoidArm.RIGHT) {
//            playerrenderer.renderRightHand(pMatrixStack, pBuffer, pCombinedLight, player);
//        } else {
//            playerrenderer.renderLeftHand(pMatrixStack, pBuffer, pCombinedLight, player);
//        }
//
//        pMatrixStack.popPose();
//    }
//
//}
