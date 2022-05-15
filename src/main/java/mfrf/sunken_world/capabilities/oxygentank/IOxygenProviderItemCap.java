package mfrf.sunken_world.capabilities.oxygentank;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IOxygenProviderItemCap {

    int getOxygen();

    int getCapacity();

    int recoverySpeed();

    void setOxygen(int oxygen);

    default int addOxygen(int amount) {
        int remain = amount + getOxygen() - getCapacity();
        if (remain > 0) {
            setOxygen(getCapacity());
            return remain;
        } else {
            setOxygen(getOxygen() + amount);
        }
        return 0;
    }

    default int extractOxygen(int amount) {
        int oxygen = getOxygen();
        setOxygen(Math.max(oxygen - amount, 0));
        return oxygen - getOxygen();
    }

    default boolean isEmpty() {
        return getOxygen() == 0;
    }

    default void recovery() {
        setOxygen(Math.min(getOxygen() + recoverySpeed(), getCapacity()));
    }


}
