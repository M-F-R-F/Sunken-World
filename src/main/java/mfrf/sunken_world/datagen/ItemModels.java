package mfrf.sunken_world.datagen;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.registry.Blocks;
import mfrf.sunken_world.registry.Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, SunkenWorld.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        singleTexture(Items.CONDUIT_PENDANT.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/conduit_pendant"));
        singleTexture(Items.GRACE_OF_SKY_PENDANT.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/grace_of_sky_pendant"));
        singleTexture(Items.GRACE_OF_OCEAN_PENDANT.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/grace_of_ocean_pendant"));
        singleTexture(Items.GRACE_OF_STRATA_PENDANT.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/grace_of_strata_pendant"));
        singleTexture(Items.OXYGEN_TANK_1.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/oxygen_tank"));
        singleTexture(Items.OXYGEN_TANK_2.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/high_pressure_oxygen_tank"));
        singleTexture(Items.OXYGEN_TANK_3.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/extreme_high_pressure_oxygen_tank"));
        singleTexture(Items.SPONGE_ON_A_STICK.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/sponge_on_a_stick"));
        singleTexture(Items.THROWABLE_SPONGE.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/throwable_sponge"));
        singleTexture(Items.THROWABLE_UNDERWATER_TORCH.get().getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/throwable_underwater_torch"));
//        regDefault(Blocks.RED_CORAL.item(),"red_coral");
    }

    private void regDefault(Item item, String path){
        singleTexture(item.getRegistryName().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/"+path));
    }
}
