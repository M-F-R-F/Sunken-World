package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.worldgen.deco_features.GloilKelpFeature;
import mfrf.sunken_world.worldgen.deco_features.MuseahroomFeature;
import mfrf.sunken_world.worldgen.deco_features.RedCoralFeature;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Features {


    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SunkenWorld.MODID);
    public static final RegistryObject<Feature<?>> GLOIL_KELP_FEATURE = FEATURES.register("gloil_kelp", GloilKelpFeature::new);
    public static final RegistryObject<Feature<?>> RED_CORAL_FEATURE = FEATURES.register("red_coral", RedCoralFeature::new);
    public static final RegistryObject<Feature<?>> MUSEAHROOM_FEATURE = FEATURES.register("museahroom", MuseahroomFeature::new);


    public class FeatureHolder {
        public static Holder GLOIL_KELP_PLACED_FEATURE;
        public static Holder RED_CORAL_PLACED_FEATURE;
        public static Holder MUSEAHROOM_PLACED_FEATURE;


        public static void registerFeatures() {
            GLOIL_KELP_PLACED_FEATURE = registerPlacedFeature("gloil_kelp_feature", new ConfiguredFeature(GLOIL_KELP_FEATURE.get(), FeatureConfiguration.NONE), NoiseBasedCountPlacement.of(80, 80.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
            RED_CORAL_PLACED_FEATURE = registerPlacedFeature("red_coral_feature", new ConfiguredFeature(RED_CORAL_FEATURE.get(), FeatureConfiguration.NONE), NoiseBasedCountPlacement.of(80, 80.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT, BiomeFilter.biome());
            MUSEAHROOM_PLACED_FEATURE = registerPlacedFeature("museahroom_feature", new ConfiguredFeature(MUSEAHROOM_FEATURE.get(), new CountConfiguration(8)), NoiseBasedCountPlacement.of(80, 80.0D, 0.0D), RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        }


        private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
            return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
        }

    }

}
