package mfrf.sunken_world.blocks.decos;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class HorizontalAttachBlock extends SimpleUnderWaterVegetation {
    public static final EnumProperty<Direction> ATTACH_DIRECTION = EnumProperty.create("attach_direction", Direction.class, Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH);

    public HorizontalAttachBlock(Properties p_49795_) {
        super(p_49795_);
        registerDefaultState(defaultBlockState().setValue(ATTACH_DIRECTION, Direction.SOUTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ATTACH_DIRECTION);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos clickedPos = pContext.getClickedPos();
        Direction clickedFace = pContext.getClickedFace();
        Direction opposite = clickedFace.getOpposite();
        Level level = pContext.getLevel();
        BlockState blockState = level.getBlockState(clickedPos.relative(opposite));

        if (Direction.Plane.HORIZONTAL.test(clickedFace) && blockState.isFaceSturdy(level, clickedPos, opposite)) {
            return defaultBlockState().setValue(ATTACH_DIRECTION, opposite);
        } else {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockState state = level.getBlockState(clickedPos.relative(direction));
                if (state.isFaceSturdy(level, clickedPos.relative(clickedFace).relative(direction), direction.getOpposite())) {
                    return defaultBlockState().setValue(ATTACH_DIRECTION, direction);
                }
            }
        }

        return null;

    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        Direction value = pState.getValue(ATTACH_DIRECTION);
        return pLevel.getBlockState(pPos.relative(value)).isFaceSturdy(pLevel, pPos, value.getOpposite());
    }
}
