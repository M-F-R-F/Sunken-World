package mfrf.sunken_world.events;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Dimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SunkenWorld.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LivingEvent {

    @SubscribeEvent
    public static void onLivingSpawnEvent(LivingSpawnEvent event) {
        if (event.getEntity().getType() == EntityType.DROWNED && event.getWorld().dimensionType().effectsLocation() == Dimensions.SUNKEN_WORLD.location()) {
            event.setResult(event.getY() < Config.SEA_LEVEL_BOTTOM.get() && event.getWorld().getRandom().nextInt(30) == 0? Event.Result.ALLOW : Event.Result.DENY);
        }
    }
}
