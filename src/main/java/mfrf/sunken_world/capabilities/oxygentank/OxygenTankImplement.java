package mfrf.sunken_world.capabilities.oxygentank;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;

public class OxygenTankImplement implements IOxygenProviderItemCap {
    private int oxygen;
    private int recovery_speed;
    private int capacity;

    private OxygenTankImplement(int capacity, int recovery_speed) {
        this.capacity = capacity;
        this.recovery_speed = recovery_speed;
    }


    @Override
    public int getOxygen() {
        return oxygen;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int recoverySpeed() {
        return recovery_speed;
    }

    @Override
    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public static void attach(ItemStack stack, int capacity, int recovery_speed) {

        CompoundTag orCreateTag = stack.getOrCreateTag();

        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("capacity", capacity);
        compoundTag.putInt("recovery_speed", recovery_speed);
        compoundTag.putInt("oxygen", capacity);
        orCreateTag.put("oxygen_tank", compoundTag);
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.putInt("oxygen", oxygen);
        compoundTag.putInt("capacity", capacity);
        compoundTag.putInt("recovery_speed", recovery_speed);
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        oxygen = nbt.getInt("oxygen");
        capacity = nbt.getInt("capacity");
        recovery_speed = nbt.getInt("recovery_speed");
    }
}
