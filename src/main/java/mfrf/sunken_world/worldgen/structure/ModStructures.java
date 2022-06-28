package mfrf.sunken_world.worldgen.structure;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {
    public static final DeferredRegister<StructureFeature<?>> DEFERRED_REGISTRY_STRUCTURE = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, SunkenWorld.MODID);
    public static final RegistryObject<StructureFeature<?>> CHURCH_STRUCTURE = DEFERRED_REGISTRY_STRUCTURE.register("world_beacon_structure", ChurchStructure::new);

    public static void register(IEventBus eventBus) {
        DEFERRED_REGISTRY_STRUCTURE.register(eventBus);
    }
}
