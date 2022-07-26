package mfrf.sunken_world.events;


import mfrf.sunken_world.Config;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Dimensions;
import mfrf.sunken_world.registry.Features;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeSetup {

    @SubscribeEvent
    public static void onBiomeLoadingEvent(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.OCEAN) {
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FeatureHolder.GLOIL_KELP_PLACED_FEATURE);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FeatureHolder.MUSEAHROOM_PLACED_FEATURE);
            event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.FeatureHolder.RED_CORAL_PLACED_FEATURE);
        }
    }

    @SubscribeEvent
    public static void onLivingSpawnEvent(LivingSpawnEvent event) {
        if (event.getEntity().getType() == EntityType.DROWNED && event.getWorld().dimensionType().effectsLocation() == Dimensions.SUNKEN_WORLD.location()) {
            event.setResult(event.getY() < Config.SEA_LEVEL_BOTTOM.get() && event.getWorld().getRandom().nextInt(30) == 0 ? Event.Result.ALLOW : Event.Result.DENY);
        }
    }
}
