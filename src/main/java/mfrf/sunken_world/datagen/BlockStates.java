package mfrf.sunken_world.datagen;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BlockStateProvider {
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SunkenWorld.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    }


}
