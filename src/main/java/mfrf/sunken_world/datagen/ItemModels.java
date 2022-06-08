package mfrf.sunken_world.datagen;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Blocks;
import mfrf.sunken_world.registry.Items;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SunkenWorld.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(Items.CONDUIT_PENDANT.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/conduit_pendant"));
//        withExistingParent(Blocks.OVERWORLD_BEACON.item().get().getRegistryName().getPath(), modLoc("block/overworld_beacon"));
    }
}
