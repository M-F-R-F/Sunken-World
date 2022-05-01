package mfrf.sunken_world.capabilities.oxygentank;

import mfrf.sunken_world.registry.Capabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OxygenTankImplement implements IOxygenProviderItemCap, ICapabilityProvider {

    private final ItemStack stack;

    public OxygenTankImplement(int capacity, int recovery_speed, ItemStack stack) {
        stack.getOrCreateTagElement("oxygen_tank").putInt("oxygen", capacity);
        stack.getOrCreateTagElement("oxygen_tank").putInt("capacity", capacity);
        stack.getOrCreateTagElement("oxygen_tank").putInt("recovery_speed", recovery_speed);
        this.stack = stack;
    }


    @Override
    public int getOxygen() {
        return stack.getTagElement("oxygen_tank").getInt("oxygen");
    }

    @Override
    public int getCapacity() {
        return stack.getTagElement("oxygen_tank").getInt("capacity");
    }

    @Override
    public int recoverySpeed() {
        return stack.getTagElement("oxygen_tank").getInt("recovery_speed");
    }

    @Override
    public void setOxygen(int oxygen) {
        stack.getTagElement("oxygen_tank").putInt("oxygen", oxygen);
    }


    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return getCapability(cap);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == Capabilities.OXYGEN_PROVIDER_ITEM_CAP_CAPABILITY) return LazyOptional.of(() -> this).cast();
        return LazyOptional.empty();
    }
}
