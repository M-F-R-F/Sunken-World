package mfrf.sunken_world.gui.nether_furnace;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import mfrf.sunken_world.SunkenWorld;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class NetherFurnaceScreen extends AbstractContainerScreen<NetherFurnaceContainer> {
    private final ResourceLocation GUI = new ResourceLocation(SunkenWorld.MODID, "textures/gui/nether_furnace.png");

    public NetherFurnaceScreen(NetherFurnaceContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        RenderSystem.setShaderTexture(0, GUI);


        if (menu.soulFireLit()) {
        blit(pPoseStack, getGuiLeft() + 85, getGuiTop() + 32, 15, 20, 176, 0, 15, 20, 256, 256);
        }

        int litProgress = menu.getLitProgress();

//        if (litProgress != 0) {
            blit(pPoseStack, getGuiLeft() + 85, getGuiTop() + 32 + 20 - litProgress, 15, litProgress, 176, 36 - litProgress, 15, litProgress, 256, 256);
//            blit(pPoseStack, getGuiLeft() + 85, getGuiTop() + 32 +20 - litProgress, 15, litProgress, 176, 36 - litProgress, 15, litProgress, 256, 256);
//        }

        renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        blit(pPoseStack, getGuiLeft() + 55, getGuiTop() + 16, 18, 18, 176, 52, 18, 18, 256, 256);

        blit(pPoseStack, getGuiLeft() + 55, getGuiTop() + menu.getBurnProgress(), 18, 18, 176, 70, 18, 18, 256, 256);

        blit(pPoseStack, getGuiLeft() + 56, getGuiTop() + 17, 16, 16, 176, 36, 16, 16, 256, 256);

        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
