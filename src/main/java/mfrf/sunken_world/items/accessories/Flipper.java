package mfrf.sunken_world.items.accessories;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Consumer;

public class Flipper extends CurioBase {
    public final Consumer<ServerPlayer> onActive;

    public Flipper(Properties pProperties, Consumer<ServerPlayer> onActive) {
        super(pProperties);
        this.onActive = onActive;
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
    }
}
