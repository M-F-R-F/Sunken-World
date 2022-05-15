package mfrf.sunken_world.render;

import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.sunken_world.Config;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

public class EquipmentHud implements IIngameOverlay {

    @Override
    public void render(ForgeIngameGui gui, PoseStack poseStack, float partialTick, int width, int height) {
        Minecraft minecraft = Minecraft.getInstance();
        if (!minecraft.options.hideGui && !minecraft.player.isSpectator() && Config.OXYGEN_TANK_HUD_ENABLED.get()) {
//            gui.getMinecraft().getTextureManager().bindTexture(minecraft.player.getLocationSkin());
        }
    }
}
