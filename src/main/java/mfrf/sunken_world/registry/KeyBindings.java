package mfrf.sunken_world.registry;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBindings {
    public static final String KEY_CATEGORIES_GEAR = "key.sunken_world.sunken_world_gear";
    public static final String ACTIVE_FLIPPER = "key.active_flipper";

    public static KeyMapping activeFlipperKeyMapping;

    public static void init() {
        activeFlipperKeyMapping = new KeyMapping(ACTIVE_FLIPPER, KeyConflictContext.IN_GAME, InputConstants.getKey("key.keyboard.b"), KEY_CATEGORIES_GEAR);
        ClientRegistry.registerKeyBinding(activeFlipperKeyMapping);
    }
}
