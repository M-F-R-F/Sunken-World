package mfrf.sunken_world.items.accessories;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class GracePendant extends CurioBase {

    private final RegistryObject<Attribute>[] attributes;

    public GracePendant(Properties pProperties, RegistryObject<Attribute>... attributes) {
        super(pProperties);
        this.attributes = attributes;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(slotContext, uuid, stack);
        for (RegistryObject<Attribute> attribute : attributes) {
            multimap.put(attribute.get(), new AttributeModifier(uuid, attribute.get().getDescriptionId(), 1.0, AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

}
