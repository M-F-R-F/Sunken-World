package mfrf.sunken_world.registry;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.SunkenWorld;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Attributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SunkenWorld.MODID);
    public static final RegistryObject<Attribute> SWIFT_DIG = ATTRIBUTES.register("swift_dig", () -> new RangedAttribute("swift_dig", Config.SWIFT_DIG_ADDITION.get(), 0.0D, Config.MAX_SWIFT_DIG.get()));
    public static final RegistryObject<Attribute> GRACE_OF_SKY = ATTRIBUTES.register("grace_of_sky", () -> new NonValueAttribute("grace_of_sky"));
    public static final RegistryObject<Attribute> GRACE_OF_OCEAN = ATTRIBUTES.register("grace_of_ocean", () -> new NonValueAttribute("grace_of_ocean"));
    public static final RegistryObject<Attribute> GRACE_OF_STRATA = ATTRIBUTES.register("grace_of_strata", () -> new NonValueAttribute("grace_of_strata"));
    public static final RegistryObject<Attribute> ADDITION_OXYGEN_CAPACITY = ATTRIBUTES.register("addition_oxygen_capacity", () -> new RangedAttribute("addition_oxygen_capacity", 0, 0, Integer.MAX_VALUE));
    public static final RegistryObject<Attribute> DISTANCE_CALLING = ATTRIBUTES.register("distance_calling",()->new NonValueAttribute("distance_calling"));
}

class NonValueAttribute extends Attribute {

    public NonValueAttribute(String pDescriptionId) {
        super(pDescriptionId, 0);
    }
}



