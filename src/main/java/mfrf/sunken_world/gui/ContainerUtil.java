package mfrf.sunken_world.gui;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import java.util.function.Consumer;

public abstract class ContainerUtil {

    public static void layoutPlayerInventorySlots(int leftCol, int topRow, Consumer<Slot> menu, IItemHandler playerInventory) {
        addSlotBox(menu, playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(menu, playerInventory, 0, leftCol, topRow, 9, 18);
    }

    protected static int addSlotRange(Consumer<Slot> consumer, IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            consumer.accept(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    protected static int addSlotBox(Consumer<Slot> consumer, IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(consumer, handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }
}
