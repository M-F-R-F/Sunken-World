package mfrf.sunken_world.blocks.water_proof_furnace;

import mfrf.sunken_world.blocks.nether_furnace.TileNetherFurnace;
import mfrf.sunken_world.registry.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class BlockWaterProofFurnace extends AbstractFurnaceBlock {
    public BlockWaterProofFurnace() {
        super(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(value -> value.getValue(BlockStateProperties.LIT) ? 13 : 0));
    }
    @Override
    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        if (!pLevel.isClientSide() && pLevel.getBlockEntity(pPos) instanceof TileWaterProofFurnace containerProvider) {
            NetworkHooks.openGui((ServerPlayer) pPlayer, containerProvider, pPos);
        }
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileWaterProofFurnace(pPos, pState);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null : createTickerHelper(pBlockEntityType, BlockEntities.WATER_PROOF_FURNACE_TILE.get(), TileWaterProofFurnace::serverTick);
    }
}
