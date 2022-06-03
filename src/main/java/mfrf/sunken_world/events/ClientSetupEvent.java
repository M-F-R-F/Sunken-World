package mfrf.sunken_world.events;

import mfrf.sunken_world.Entities.bubble.BubbleRender;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Entities;
import mfrf.sunken_world.registry.KeyBindings;
import mfrf.sunken_world.render.EquipmentHud;
import net.minecraft.client.player.inventory.Hotbar;
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
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Entities.BUBBLE.get(), BubbleRender::new);
    }

}
