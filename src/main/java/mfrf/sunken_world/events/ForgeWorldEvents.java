package mfrf.sunken_world.events;


import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Features;
import mfrf.sunken_world.worldgen.deco_features.GloilKelpFeature;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeWorldEvents {

    @SubscribeEvent
    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.OCEAN) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FeatureHolder.GLOIL_KELP_PLACED_FEATURE);
        }
    }
}
