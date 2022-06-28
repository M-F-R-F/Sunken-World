package mfrf.sunken_world;

import mfrf.sunken_world.events.EventAboutPlayer;
import mfrf.sunken_world.registry.*;
import mfrf.sunken_world.worldgen.OverWorldBiomeRegion;
import mfrf.sunken_world.worldgen.structure.ModStructures;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terrablender.api.Regions;

@Mod("sunken_world")
@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SunkenWorld {
    public static final String MODID = "sunken_world";

    private static final Logger LOGGER = LogManager.getLogger();

    public SunkenWorld() {
        MinecraftForge.EVENT_BUS.register(new EventAboutPlayer());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        ModInit();
    }


    private static void ModInit() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Blocks.BLOCKS.register(modEventBus);
        Items.ITEMS.register(modEventBus);
        Attributes.ATTRIBUTES.register(modEventBus);
        Entities.ENTITIES.register(modEventBus);
        BlockEntities.BLOCK_ENTITIES.register(modEventBus);
        GuiContainers.CONTAINERS.register(modEventBus);
        ModStructures.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new OverWorldBiomeRegion(new ResourceLocation(SunkenWorld.MODID, "overworld"), 2));
        });
    }

}
