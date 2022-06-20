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

    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
//        addBiome(mapper, Climate.parameters(
//                FULL_RANGE,
//                FULL_RANGE,
//                FULL_RANGE,
//                FULL_RANGE,
////                Climate.Parameter.span(-1, 0),
//                FULL_RANGE,
//                FULL_RANGE,
//                0), Biomes.MYSTERY_OCEAN);

        //won't completely replace, just take some place.
        addModifiedVanillaOverworldBiomes(mapper,modifiedVanillaOverworldBuilder -> {
            modifiedVanillaOverworldBuilder.replaceBiome(net.minecraft.world.level.biome.Biomes.DEEP_LUKEWARM_OCEAN, mfrf.sunken_world.registry.Biomes.MYSTERY_OCEAN);
        });

        //todo
    }

}
