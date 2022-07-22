package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.worldgen.structure.ChurchStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, SunkenWorld.MODID);
    public static final RegistryObject<StructureFeature<?>> CHURCH_STRUCTURE = STRUCTURES.register("world_beacon_structure", ChurchStructure::new);

}
