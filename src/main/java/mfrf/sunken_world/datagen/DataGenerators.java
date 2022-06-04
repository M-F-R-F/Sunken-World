package mfrf.sunken_world.datagen;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {

        }if (event.includeClient()) {
            generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
//            generator.addProvider(new BlockTagAttaches(generator, event.getExistingFileHelper()));

        }
    }
}