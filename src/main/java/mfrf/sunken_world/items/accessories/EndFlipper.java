package mfrf.sunken_world.items.accessories;

import mfrf.sunken_world.Config;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import top.theillusivec4.curios.api.SlotContext;

import java.util.function.Consumer;

public class EndFlipper extends Flipper {
    public EndFlipper(Properties pProperties, Double speedBoost) {
        super(pProperties, speedBoost);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);

        LivingEntity entity = slotContext.entity();
        if (entity instanceof ServerPlayer serverPlayer) {
            if (serverPlayer.isSwimming()) {
                double value = Math.floor(serverPlayer.getAttribute(ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation("forge", "swim_speed"))).getValue() / 2);
                int energy = getEnergy(stack);
                int i = (int) (energy + value);
                if (i <= Config.END_FLIPPER_ENERGY_CAPACITY.get()) {
                    setEnergy(stack, i);
                    //todo make it capability
                }
            }
        }
    }

    public static int getEnergy(ItemStack stack) {
        assertTagEnergy(stack);
        return stack.getOrCreateTag().getInt("teleport_energy");
    }

    public static void setEnergy(ItemStack stack, int energy) {
        assertTagEnergy(stack);
        stack.getOrCreateTag().putInt("teleport_energy", energy);
    }

    public static void assertTagEnergy(ItemStack stack) {
        CompoundTag orCreateTag = stack.getOrCreateTag();
        if (!orCreateTag.contains("teleport_energy")) {
            orCreateTag.putInt("teleport_energy", 0);
        }
    }
}
