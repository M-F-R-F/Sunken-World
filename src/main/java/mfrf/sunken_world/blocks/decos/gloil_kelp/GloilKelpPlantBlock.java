package mfrf.sunken_world.blocks.decos.gloil_kelp;

import mfrf.sunken_world.registry.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;

public class GloilKelpPlantBlock extends GrowingPlantBodyBlock implements LiquidBlockContainer {
    public static final IntegerProperty OIL_SEED = IntegerProperty.create("oil_seed", 0, 5);

    public GloilKelpPlantBlock(Properties p_53886_) {
        super(p_53886_, Direction.UP, Shapes.block(), true);
        registerDefaultState(defaultBlockState().setValue(OIL_SEED, 0));
    }

    protected boolean canAttachTo(BlockState pState) {
        return !pState.is(net.minecraft.world.level.block.Blocks.MAGMA_BLOCK);
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, Random pRandom, BlockPos pPos, BlockState pState) {
        pLevel.setBlockAndUpdate(pPos, pState.setValue(OIL_SEED, Math.max(pState.getValue(OIL_SEED) + 1, 5)));
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return pState.getValue(OIL_SEED) >= 5;
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) Blocks.GLOIL_KELP_ROOT.block().get();
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(OIL_SEED);
    }
}
