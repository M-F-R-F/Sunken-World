package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.worldgen.deco_features.GloilKelpFeature;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Features {


    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, SunkenWorld.MODID);
    public static final RegistryObject<Feature<?>> GLOIL_KELP_FEATURE = FEATURES.register("gloil_kelp", GloilKelpFeature::new);


    public class FeatureHolder {
        public static Holder GLOIL_KELP_PLACED_FEATURE;


        public static void registerFeatures() {
            GLOIL_KELP_PLACED_FEATURE = registerPlacedFeature("gloil_kelp_feature",new ConfiguredFeature(GLOIL_KELP_FEATURE.get(),FeatureConfiguration.NONE), NoiseBasedCountPlacement.of(80, 80.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());

        }


        private static <C extends FeatureConfiguration, F extends Feature<C>> Holder<PlacedFeature> registerPlacedFeature(String registryName, ConfiguredFeature<C, F> feature, PlacementModifier... placementModifiers) {
            return PlacementUtils.register(registryName, Holder.direct(feature), placementModifiers);
        }

    }

}
