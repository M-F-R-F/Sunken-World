package mfrf.sunken_world;

import mfrf.sunken_world.events.EventAboutPlayer;
import mfrf.sunken_world.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("sunken_world")
public class SunkenWorld {
    public static final String MODID = "sunken_world";

    private static final Logger LOGGER = LogManager.getLogger();

    public SunkenWorld() {
        MinecraftForge.EVENT_BUS.register(new EventAboutPlayer());
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
    }

}
