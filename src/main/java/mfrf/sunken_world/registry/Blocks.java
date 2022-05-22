package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.blocks.OxygenCoral;
import mfrf.sunken_world.blocks.nether_furnace.BlockNetherProofFurnace;
import mfrf.sunken_world.blocks.water_proof_furnace.BlockWaterProofFurnace;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Properties;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SunkenWorld.MODID);

    //    public static final RegistryObject<ModifiedWaterBlock> MODIFIED_WATER = BLOCKS.register("water", ModifiedWaterBlock::new);
    public static final RegistryObject<Block> OXYGEN_CORAL = BLOCKS.register("oxygen_coral", () -> new OxygenCoral(BlockBehaviour.Properties.of(Material.STONE, DyeColor.GRAY)));
    public static final RegistryObject<Block> BLOCK_WATER_PROOF_FURNACE = BLOCKS.register("water_proof_furnace", BlockWaterProofFurnace::new);
    public static final RegistryObject<Block> BLOCK_NETHER_FURNACE = BLOCKS.register("nether_furnace", BlockNetherProofFurnace::new);

}
