package mfrf.sunken_world.items.accessories;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.wrapper.EntityEquipmentInvWrapper;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.function.Consumer;

public class Flipper extends CurioBase {
    private final Double speedBoost;

    public Flipper(Properties pProperties, Double speedBoost) {
        super(pProperties);
        this.speedBoost = speedBoost;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@Nullable EquipmentSlot slot) {
//        Multimap<Attribute, AttributeModifier> multimap = LinkedHashMultimap.create(super.getDefaultAttributeModifiers(slot));
//        UUID uuid = new UUID(slot.toString().hashCode(),0);
//        if(slot == EquipmentSlot.FEET) {
//            Attribute swimSpeed = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("forge", "swim_speed"));
//            multimap.put(swimSpeed, new AttributeModifier(uuid, "Flipper Swim Speed", 0.1, AttributeModifier.Operation.ADDITION));
//        }
//        return multimap;
//    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = super.getAttributeModifiers(slotContext, uuid, stack);
        Attribute swimSpeed = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("forge", "swim_speed"));
        attributeModifiers.put(swimSpeed, new AttributeModifier(uuid, "Flipper Swim Speed", speedBoost, AttributeModifier.Operation.ADDITION));
        return attributeModifiers;
        //todo add to slot type
    }

}
