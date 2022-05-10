package mfrf.sunken_world.events;

import com.mojang.math.Vector3f;
import mfrf.sunken_world.network.try_teleport.PakcetTryTeleport;
import mfrf.sunken_world.network.try_teleport.TryTeleportChannel;
import mfrf.sunken_world.registry.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.fml.common.Mod;

public class KeyInputEvent {

    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.activeFlipperKeyMapping.consumeClick()) {
            TryTeleportChannel.sendToServer(new PakcetTryTeleport(new Vector3f(Minecraft.getInstance().player.getLookAngle())));
//            Minecraft.getInstance().player.getViewVector()
        }
    }
}
