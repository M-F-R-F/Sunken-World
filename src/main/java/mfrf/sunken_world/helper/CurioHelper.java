package mfrf.sunken_world.helper;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class CurioHelper {

    public static ItemStack findAnyItem(LivingEntity entity, String id, Predicate<ItemStack> stackPredicate) {
        AtomicReference<ItemStack> stack = new AtomicReference<>(ItemStack.EMPTY);
        CuriosApi.getCuriosHelper().getCuriosHandler(entity).ifPresent(iCuriosItemHandler -> {
            iCuriosItemHandler.getStacksHandler(id).ifPresent(iCurioStacksHandler -> {
                IDynamicStackHandler stacks = iCurioStacksHandler.getStacks();
                for (int i = 0; i < stacks.getSlots(); i++) {
                    ItemStack stackInSlot = stacks.getStackInSlot(i);
                    if (stackPredicate.test(stackInSlot)) {
                        stack.set(stackInSlot);
                    }
                }
            });
        });
        return stack.get();
    }
}
