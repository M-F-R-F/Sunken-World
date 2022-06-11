package mfrf.sunken_world.events;

import com.mojang.math.Vector3f;
import mfrf.sunken_world.network.try_teleport.PakcetActiveFlipperEnhance;
import mfrf.sunken_world.network.try_teleport.TryTeleportChannel;
import mfrf.sunken_world.registry.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import org.lwjgl.glfw.GLFW;

public class KeyInputEvent {

    public static void onKeyInput(InputEvent.KeyInputEvent event) {
        if (KeyBindings.activeFlipperKeyMapping.consumeClick()) {
            TryTeleportChannel.sendToServer(new PakcetActiveFlipperEnhance(new Vector3f(Minecraft.getInstance().player.getLookAngle())));
//            Minecraft.getInstance().player.getViewVector()
        }
//        if (event.getAction() == GLFW.GLFW_RELEASE && KeyBindings.activeFlipperKeyMapping.getKey().getNumericKeyValue().orElse(-1) == event.getKey()){
//            TryTeleportChannel.sendToServer(new PakcetActiveFlipperEnhance(new Vector3f(Minecraft.getInstance().player.getLookAngle())));
//        }
    }

}
