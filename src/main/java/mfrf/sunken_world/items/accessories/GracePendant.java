package mfrf.sunken_world.items.accessories;

import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class GracePendant extends CurioBase {

    private final Attribute[] attributes;

    public GracePendant(Properties pProperties, Attribute... attributes) {
        super(pProperties);
        this.attributes = attributes;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> multimap = super.getAttributeModifiers(slotContext, uuid, stack);
        for (Attribute attribute : attributes) {
            multimap.put(attribute, new AttributeModifier(uuid, attribute.getDescriptionId(), 1.0, AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }

}
