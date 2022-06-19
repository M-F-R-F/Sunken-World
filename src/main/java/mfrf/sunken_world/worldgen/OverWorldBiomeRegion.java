package mfrf.sunken_world.worldgen;

import com.mojang.datafixers.util.Pair;
import mfrf.sunken_world.registry.Biomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.Regions;

import java.util.function.Consumer;

public class OverWorldBiomeRegion extends Region {
    public OverWorldBiomeRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addBiome(mapper, Climate.parameters(ParameterUtils.Temperature.COOL.parameter(), ParameterUtils.Humidity.HUMID.parameter(), ParameterUtils.Continentalness.DEEP_OCEAN.parameter(), ParameterUtils.Erosion.EROSION_2.parameter(), ParameterUtils.Weirdness.HIGH_SLICE_VARIANT_ASCENDING.parameter(), ParameterUtils.Depth.FULL_RANGE.parameter(), 5), Biomes.MYSTERY_OCEAN);
    }

}
