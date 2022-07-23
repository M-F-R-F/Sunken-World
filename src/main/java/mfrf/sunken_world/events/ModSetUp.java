package mfrf.sunken_world.events;

import mfrf.sunken_world.Entities.harmless.spadefish.EntitySpadeFish;
import mfrf.sunken_world.Entities.mobs.anomalocaris.MobEntityAnomalocaris;
import mfrf.sunken_world.Entities.mobs.piranha.MobEntityPiranha;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.network.sync_player_info.SyncPlayerInfoChannel;
import mfrf.sunken_world.network.try_teleport.TryTeleportChannel;
import mfrf.sunken_world.registry.Entities;
import mfrf.sunken_world.registry.Features;
import mfrf.sunken_world.worldgen.OverWorldBiomeRegion;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import terrablender.api.Regions;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetUp {

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        TryTeleportChannel.register();
        SyncPlayerInfoChannel.register();
        Features.FeatureHolder.registerFeatures();
    }

    @SubscribeEvent
    public static void regStructure(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new OverWorldBiomeRegion(new ResourceLocation(SunkenWorld.MODID, "overworld"), 2));
        });
    }

    @SubscribeEvent
    public static void onAttributesCreate(EntityAttributeCreationEvent event){
        event.put(Entities.MOB_ENTITY_PIRANHA.get(), MobEntityPiranha.createAttributes().build());
        event.put(Entities.SPADE_FISH.get(), EntitySpadeFish.prepareAttributes().build());
        event.put(Entities.ANOMALOCARIS.get(), MobEntityAnomalocaris.prepareAttributes().build());
    }



}
