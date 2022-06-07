package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTab {
    public static final CreativeModeTab TAB = new CreativeModeTab(SunkenWorld.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.SWIM_GLASS.get());
        }
    };
}
