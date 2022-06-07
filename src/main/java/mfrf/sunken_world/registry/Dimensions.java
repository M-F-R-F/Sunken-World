package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

public class Dimensions {
    public static final ResourceKey<Level> SUNKEN_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(SunkenWorld.MODID, "sunken_world"));
}
