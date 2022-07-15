package mfrf.sunken_world.blocks.decos;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class HorizontalAttachBlock extends Block {
    public static final EnumProperty<Direction> ATTACH_DIRECTION = EnumProperty.create("attach_direction", Direction.class, Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH);

    public HorizontalAttachBlock(Properties p_49795_) {
        super(p_49795_);
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

        if (Direction.Plane.HORIZONTAL.test(clickedFace) && canSupportCenter(level, clickedPos, opposite)) {
            return defaultBlockState().setValue(ATTACH_DIRECTION, clickedFace);
        } else {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                if (canSupportCenter(level, clickedPos.relative(clickedFace).relative(direction), direction.getOpposite())) {
                    return defaultBlockState().setValue(ATTACH_DIRECTION, direction);
                }
            }
        }

        return null;

    }
}
