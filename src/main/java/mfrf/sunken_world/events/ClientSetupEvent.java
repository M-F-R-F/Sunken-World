package mfrf.sunken_world.events;

import mfrf.sunken_world.Entities.technical.under_water_torch.UnderWaterTorchRender;
import mfrf.sunken_world.Entities.technical.water_block_projectile.WaterBlockProjectileRender;
import mfrf.sunken_world.Entities.technical.bubble.BubbleRender;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.helper.ClientHelper;
import mfrf.sunken_world.registry.BlockEntities;
import mfrf.sunken_world.registry.Blocks;
import mfrf.sunken_world.registry.Entities;
import mfrf.sunken_world.registry.KeyBindings;
import mfrf.sunken_world.render.EquipmentHud;
import mfrf.sunken_world.render.tile_render.TileOverworldBeaconRender;
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
public class ClientSetupEvent {

    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.addListener(KeyInputEvent::onKeyInput);
        KeyBindings.init();
        OverlayRegistry.registerOverlayTop("oxygen_tank", EquipmentHud.INSTANCE);
        ClientHelper.setOxygenTank = EquipmentHud::setPercent;

        registerTileRender();
    }

    @SubscribeEvent
    public static void setupRenderTypes(final FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(Blocks.GLOIL_KELP_ROOT.block().get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Blocks.GLOIL_KELP_BODY.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Entities.BUBBLE.get(), BubbleRender::new);
        event.registerEntityRenderer(Entities.WATER_BLOCK_PROJECTILE.get(), WaterBlockProjectileRender::new);
        event.registerEntityRenderer(Entities.UNDERWATER_TORCH.get(), UnderWaterTorchRender::new);
    }

    private static void registerTileRender() {
        BlockEntityRenderers.register(BlockEntities.WORLD_BEACON_TILE.get(), TileOverworldBeaconRender::new);
    }

}
