package mfrf.sunken_world.datagen;

import mfrf.sunken_world.SunkenWorld;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockSatiatedAttaches extends BlockStateProvider {
    public BlockSatiatedAttaches(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SunkenWorld.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // TODO Auto-generated method stub
    }


}
