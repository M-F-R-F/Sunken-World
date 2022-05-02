package mfrf.sunken_world.events;

import mfrf.sunken_world.registry.Attributes;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.OptionalDouble;

public class PlayerEvent {

    @SubscribeEvent
    public void onDig(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event) {
        if (event.getPlayer().isEyeInFluid(FluidTags.WATER)) {
            OptionalDouble attributeModifiers = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.SWIFT_DIG.get()).stream().mapToDouble(AttributeModifier::getAmount).max();
            attributeModifiers.ifPresent(value -> event.setNewSpeed((float) (event.getNewSpeed() * value)));
        }
        //todo check
    }
}
