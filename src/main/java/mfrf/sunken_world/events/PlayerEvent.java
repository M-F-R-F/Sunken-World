package mfrf.sunken_world.events;

import mfrf.sunken_world.registry.Attributes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collection;
import java.util.OptionalDouble;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlayerEvent {

//    @SubscribeEvent
//    public void onSunken(net.minecraftforge.event.entity.player.PlayerEvent event) {

    @SubscribeEvent
    public void onDig(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event) {
        OptionalDouble attributeModifiers = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getAttributeModifiers(null).get(Attributes.SWIFT_DIG.get()).stream().mapToDouble(AttributeModifier::getAmount).max();
        attributeModifiers.ifPresent(value -> event.setNewSpeed((float) (event.getNewSpeed() * value)));
        //todo check
    }
}
