package mfrf.sunken_world.capabilities.oxygentank;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IOxygenProviderItemCap extends INBTSerializable<CompoundTag> {

    int getOxygen();

    int getCapacity();

    int recoverySpeed();

    void setOxygen(int oxygen);

    default void increase() {
        setOxygen(Math.min(getOxygen() + recoverySpeed(), getCapacity()));
    }


}
