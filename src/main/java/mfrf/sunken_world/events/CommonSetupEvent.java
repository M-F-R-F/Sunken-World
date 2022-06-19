package mfrf.sunken_world.events;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.network.sync_player_info.SyncPlayerInfoChannel;
import mfrf.sunken_world.network.try_teleport.TryTeleportChannel;
import mfrf.sunken_world.registry.Entities;
import mfrf.sunken_world.worldgen.OverWorldBiomeRegion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.api.Regions;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEvent {

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        TryTeleportChannel.register();
        SyncPlayerInfoChannel.register();

        event.enqueueWork(() -> {
            Regions.register(new OverWorldBiomeRegion(new ResourceLocation(SunkenWorld.MODID, "overworld_biome_region"), 2));
        });
    }

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
    }

}
