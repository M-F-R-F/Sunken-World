package mfrf.sunken_world.gui.waterproof_furnace;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.render.EquipmentHud;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WaterProofFurnaceScreen extends AbstractContainerScreen<WaterproofFurnaceContainer> {
    private final ResourceLocation GUI = new ResourceLocation(SunkenWorld.MODID, "textures/gui/water_proof_furnace.png");

    public WaterProofFurnaceScreen(WaterproofFurnaceContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        RenderSystem.setShaderTexture(0, GUI);
        int i = this.leftPos;
        int j = this.topPos;
        if (this.menu.isLit()) {
            int k = this.menu.getLitProgress();
            this.blit(pPoseStack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
        }

        int l = this.menu.getBurnProgress();
        this.blit(pPoseStack, i + 79, j + 34, 176, 14, l + 1, 16);


        pPoseStack.pushPose();
        RenderSystem.setShaderTexture(0, EquipmentHud.OXYGEN_TANK_HUD);
        float deg = (((1 - menu.getPercent()) * 270) - 45);
//
        pPoseStack.translate(leftPos+154.5, topPos+42.5, 0);
        pPoseStack.mulPose(Vector3f.ZP.rotationDegrees(-deg));
//            poseStack.mulPose(Vector3f.ZP.rotationDegrees(-deg));
        GuiComponent.blit(pPoseStack, 0, 0, 12, 1, 0, 31, 12, 1, 32, 32);
        pPoseStack.popPose();

        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
