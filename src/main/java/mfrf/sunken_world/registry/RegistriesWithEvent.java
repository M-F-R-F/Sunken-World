package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistriesWithEvent {

    @SubscribeEvent
    public static void onRegistryCurios(InterModEnqueueEvent event) {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, SlotTypePreset.HEAD.getMessageBuilder().size(2)::build);
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, SlotTypePreset.NECKLACE.getMessageBuilder().size(2)::build);
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, SlotTypePreset.BACK.getMessageBuilder().size(1)::build);
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("flippers").icon(new ResourceLocation(SunkenWorld.MODID, "items/curios_slots/flippers")).priority(500).build());
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("oxygen_tank").icon(new ResourceLocation(SunkenWorld.MODID, "items/curios_slots/oxygen_tank")).priority(81).build());

    }

    @SubscribeEvent
    public static void onRegistrySprite(TextureStitchEvent.Pre event) {
        event.addSprite(new ResourceLocation(SunkenWorld.MODID, "items/curios_slots/flippers"));
        event.addSprite(new ResourceLocation(SunkenWorld.MODID, "items/curios_slots/oxygen_tank"));
    }

}
