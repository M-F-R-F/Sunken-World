package mfrf.sunken_world.events;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.network.try_teleport.TryTeleportChannel;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvent {

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        TryTeleportChannel.register();
    }
}
