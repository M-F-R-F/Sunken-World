package mfrf.sunken_world.blocks.nether_furnace;

import mfrf.sunken_world.registry.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class BlockNetherFurnace extends AbstractFurnaceBlock {
    public BlockNetherFurnace() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F).lightLevel(value -> value.getValue(BlockStateProperties.LIT) ? 13 : 0));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            ItemStack itemInHand = pPlayer.getItemInHand(pHand);
            if(itemInHand.getItem() instanceof FlintAndSteelItem){
                TileNetherFurnace blockEntity = (TileNetherFurnace) pLevel.getBlockEntity(pPos);
                blockEntity.nether_fire_burned_time = 1;
                blockEntity.nether_fire_lit = true;
                blockEntity.setChanged();
                itemInHand.setDamageValue(itemInHand.getDamageValue()+1);
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    protected void openContainer(Level pLevel, BlockPos pPos, Player pPlayer) {
        if (!pLevel.isClientSide() && pLevel.getBlockEntity(pPos) instanceof TileNetherFurnace containerProvider) {
            NetworkHooks.openGui((ServerPlayer) pPlayer, containerProvider, pPos);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TileNetherFurnace(pPos, pState);
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return pLevel.isClientSide ? null : createTickerHelper(pBlockEntityType, BlockEntities.NETHER_FURNACE_TILE.get(), TileNetherFurnace::serverTick);
    }
}
