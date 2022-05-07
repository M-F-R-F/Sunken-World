package mfrf.sunken_world.items.accessories;

import com.google.common.collect.Multimap;
import mfrf.sunken_world.Config;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class JetFlipper extends Flipper {

    public JetFlipper(Properties pProperties, Double speedBoost) {
        super(pProperties, speedBoost);
    }

    /**
     * this method will be tick
     * yes, it's dirty and ugly
     * but convenient!
     */
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers = super.getAttributeModifiers(slotContext, uuid, stack);

        if (isJetBoostActivated(stack) && costOxygen(slotContext.entity())) {
            Attribute swimSpeed = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("forge", "swim_speed"));
            attributeModifiers.removeAll(swimSpeed);
            attributeModifiers.put(swimSpeed, new AttributeModifier(uuid, "Flipper Swim Speed", Config.JET_FLIPPER_SPEED_ACTIVATED_BOOST.get(), AttributeModifier.Operation.ADDITION));
        }

        return attributeModifiers;
    }

    private boolean isJetBoostActivated(ItemStack stack) {
        CompoundTag orCreateTag = stack.getOrCreateTag();
        if (orCreateTag.contains("jet_boost_activated")) {
            return orCreateTag.getBoolean("jet_boost_activated");
        } else {
            orCreateTag.putBoolean("jet_boost_activated", false);
        }
        return false;
    }

    private boolean costOxygen(Entity entity) {
        int airSupply = entity.getAirSupply();
        Integer cost = Config.JET_FLIPPER_COST_ON_ACTIVATED_PER_TICK.get();
        if (airSupply >= cost) {
            entity.setAirSupply(airSupply - cost);
            return true;
        } else {
            return false;
        }
    }
}
