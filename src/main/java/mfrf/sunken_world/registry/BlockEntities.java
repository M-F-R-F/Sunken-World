package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.blocks.nether_furnace.TileNetherFurnace;
import mfrf.sunken_world.blocks.overworld_beacon.TileOverworldBeacon;
import mfrf.sunken_world.blocks.water_proof_furnace.TileWaterProofFurnace;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SunkenWorld.MODID);
    public static final RegistryObject<BlockEntityType<TileWaterProofFurnace>> WATER_PROOF_FURNACE_TILE = BLOCK_ENTITIES.register("water_proof_furnace", () -> BlockEntityType.Builder.of(TileWaterProofFurnace::new, Blocks.WATER_PROOF_FURNACE.block().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileNetherFurnace>> NETHER_FURNACE_TILE = BLOCK_ENTITIES.register("nether_furnace", () -> BlockEntityType.Builder.of(TileNetherFurnace::new, Blocks.NETHER_FURNACE.block().get()).build(null));
    public static final RegistryObject<BlockEntityType<TileOverworldBeacon>> OVERWORLD_BEACON_TILE = BLOCK_ENTITIES.register("overworld_beacon", () -> BlockEntityType.Builder.of(TileOverworldBeacon::new, Blocks.OVERWORLD_BEACON.block().get()).build(null));

}
