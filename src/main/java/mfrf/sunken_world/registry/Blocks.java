package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.blocks.OxygenCoral;
import mfrf.sunken_world.blocks.decos.gloil_kelp.GloilKelpPlantBlock;
import mfrf.sunken_world.blocks.decos.gloil_kelp.GloilKelpRootBlock;
import mfrf.sunken_world.blocks.nether_furnace.BlockNetherProofFurnace;
import mfrf.sunken_world.blocks.overworld_beacon.WorldBeacon;
import mfrf.sunken_world.blocks.water_proof_furnace.BlockWaterProofFurnace;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SunkenWorld.MODID);

    //    public static final RegistryObject<ModifiedWaterBlock> MODIFIED_WATER = BLOCKS.register("water", ModifiedWaterBlock::new);
    public static final BlockAndItem OXYGEN_CORAL = blockAndItem("oxygen_coral", () -> new OxygenCoral(BlockBehaviour.Properties.of(Material.STONE, DyeColor.GRAY)), Items.DEFAULT_PROPERTIES);
    public static final BlockAndItem NETHER_FURNACE = blockAndItem("nether_furnace", BlockNetherProofFurnace::new, Items.DEFAULT_PROPERTIES);
    public static final BlockAndItem WORLD_BEACON = blockAndItem("world_beacon", () -> new WorldBeacon(BlockBehaviour.Properties.of(Material.STONE, DyeColor.GRAY).lightLevel(state -> state.getValue(WorldBeacon.CHARGE) ? 15 : 0)), Items.DEFAULT_PROPERTIES);
    public static final RegistryObject<Block> WATER_PROOF_FURNACE = BLOCKS.register("water_proof_furnace", BlockWaterProofFurnace::new);

    public static final RegistryObject<Block> GLOIL_KELP_BODY = BLOCKS.register("gloil_kelp_plant", () -> new GloilKelpPlantBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS).noOcclusion().lightLevel(state -> state.getValue(GloilKelpPlantBlock.OIL_SEED) > 0 ? 10 : 0)));
    public static final BlockAndItem GLOIL_KELP_ROOT = blockAndItem("gloil_kelp_root", () -> new GloilKelpRootBlock(BlockBehaviour.Properties.of(Material.WATER_PLANT).noCollission().randomTicks().instabreak().sound(SoundType.WET_GRASS).noOcclusion()), Items.DEFAULT_PROPERTIES);

    public static <B extends Block> BlockAndItem blockAndItem(String s, Supplier<B> supplier, Item.Properties properties) {
        RegistryObject<B> object = BLOCKS.register(s, supplier);
        RegistryObject<BlockItem> register = Items.ITEMS.register(s, () -> new BlockItem(object.get(), properties));
        return new BlockAndItem(object, register);
    }

    public static <B extends Block> BlockAndItem blockAndItem(String s, Supplier<B> block, Supplier<BlockItem> item) {
        return new BlockAndItem(BLOCKS.register(s, block), Items.ITEMS.register(s, item));
    }

    public record BlockAndItem(RegistryObject<? extends Block> block, RegistryObject<? extends Item> item) {
    }
}
