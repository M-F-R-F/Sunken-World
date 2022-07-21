package mfrf.sunken_world.events;

import mfrf.sunken_world.Entities.harmless.spadefish.SpadeFishModel;
import mfrf.sunken_world.Entities.harmless.spadefish.SpadeFishRenderer;
import mfrf.sunken_world.Entities.mobs.piranha.PiranhaModel;
import mfrf.sunken_world.Entities.mobs.piranha.PiranhaRenderer;
import mfrf.sunken_world.Entities.technical.bubble.BubbleRender;
import mfrf.sunken_world.Entities.technical.under_water_torch.UnderWaterTorchRender;
import mfrf.sunken_world.Entities.technical.water_block_projectile.WaterBlockProjectileRender;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.gui.nether_furnace.NetherFurnaceScreen;
import mfrf.sunken_world.gui.waterproof_furnace.WaterProofFurnaceScreen;
import mfrf.sunken_world.helper.ClientHelper;
import mfrf.sunken_world.registry.*;
import mfrf.sunken_world.render.EquipmentHud;
import mfrf.sunken_world.render.tile_render.TileOverworldBeaconRenderer;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientSetup {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(KeyInputEvent::onKeyInput);
        KeyBindings.init();
        OverlayRegistry.registerOverlayTop("oxygen_tank", EquipmentHud.INSTANCE);
        ClientHelper.setOxygenTank = EquipmentHud::setPercent;

        registerTileRender();
    }

    @SubscribeEvent
    public static void setupGUI(final FMLClientSetupEvent event) {
        MenuScreens.register(Containers.NETHER_FURNACE.get(), NetherFurnaceScreen::new);
        MenuScreens.register(Containers.WATER_PROOF_FURNACE.get(), WaterProofFurnaceScreen::new);
    }


    @SubscribeEvent
    public static void setupRenderTypes(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(Blocks.GLOIL_KELP_ROOT.block().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.GLOIL_KELP_BODY.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.RED_CORAL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.MUSEAHROOM.block().get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Entities.BUBBLE.get(), BubbleRender::new);
        event.registerEntityRenderer(Entities.WATER_BLOCK_PROJECTILE.get(), WaterBlockProjectileRender::new);
        event.registerEntityRenderer(Entities.UNDERWATER_TORCH.get(), UnderWaterTorchRender::new);
        event.registerEntityRenderer(Entities.MOB_ENTITY_PIRANHA.get(), PiranhaRenderer::new);
        event.registerEntityRenderer(Entities.SPADE_FISH.get(), SpadeFishRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PiranhaModel.PIRANHA_LAYER, PiranhaModel::createBodyLayer);
        event.registerLayerDefinition(SpadeFishModel.LAYER_LOCATION, SpadeFishModel::createBodyLayer);
    }

    private static void registerTileRender() {
        BlockEntityRenderers.register(BlockEntities.WORLD_BEACON_TILE.get(), TileOverworldBeaconRenderer::new);
    }
}
