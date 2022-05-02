package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Attributes {

    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, SunkenWorld.MODID);
    public static final RegistryObject<Attribute> SWIFT_DIG = ATTRIBUTES.register("swift_dig", () -> new RangedAttribute("swift_dig", 0.5D, 0.0D, 1.5D));

}



