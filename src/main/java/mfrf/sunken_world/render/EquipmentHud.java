package mfrf.sunken_world.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import mfrf.sunken_world.Config;
import mfrf.sunken_world.SunkenWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

public enum EquipmentHud implements IIngameOverlay {
    INSTANCE;
    private static final ResourceLocation OXYGEN_TANK_HUD = new ResourceLocation(SunkenWorld.MODID, "textures/gui/oxygen_ui.png");
    private static float percent = -1f;


    @Override
    public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!minecraft.options.hideGui && !minecraft.player.isSpectator() && Config.OXYGEN_TANK_HUD_ENABLED.get() && percent != -1) {
            minecraft.getTextureManager().bindForSetup(OXYGEN_TANK_HUD);
            poseStack.pushPose();
            RenderSystem.setShaderTexture(0, OXYGEN_TANK_HUD);
            GuiComponent.blit(poseStack, 0, 100, 29, 29, 0, 0, 29, 29, 32, 32);


            poseStack.pushPose();
            float deg = (((1 - percent) * 270) - 45);

            poseStack.translate(14.5, 114.5, 0);
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(-deg));
//            poseStack.mulPose(Vector3f.ZP.rotationDegrees(-deg));
            GuiComponent.blit(poseStack, 0, 0, 12, 1, 0, 31, 12, 1, 32, 32);
            poseStack.popPose();
            poseStack.popPose();
        }
    }

    public static void setPercent(float percent) {
        EquipmentHud.percent = percent;
    }
}
