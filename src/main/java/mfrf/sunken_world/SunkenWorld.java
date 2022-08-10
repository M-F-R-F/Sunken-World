package mfrf.sunken_world;

import mfrf.sunken_world.events.PlayerEvents;
import mfrf.sunken_world.registry.*;
import mfrf.sunken_world.registry.ModStructures;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("sunken_world")
@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SunkenWorld {
    public static final String MODID = "sunken_world";

    private static final Logger LOGGER = LogManager.getLogger();

    public SunkenWorld() {
        MinecraftForge.EVENT_BUS.register(new PlayerEvents());
        regConfig();
        ModInit();
    }


    private static void ModInit() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        Blocks.BLOCKS.register(modEventBus);
        Items.ITEMS.register(modEventBus);
        Attributes.ATTRIBUTES.register(modEventBus);
        Entities.ENTITIES.register(modEventBus);
        BlockEntities.BLOCK_ENTITIES.register(modEventBus);
        Features.FEATURES.register(modEventBus);
        GuiContainers.CONTAINERS.register(modEventBus);
        ModStructures.STRUCTURES.register(modEventBus);
        Containers.CONTAINERS.register(modEventBus);
    }

    private void regConfig() {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        Config.register(COMMON_BUILDER);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
    }


}
