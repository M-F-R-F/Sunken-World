package mfrf.sunken_world.blocks.decos;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RedCoral extends HorizontalAttachBlock{
    public RedCoral(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction value = pState.getValue(HorizontalAttachBlock.ATTACH_DIRECTION);
        return switch (value){
            case NORTH ->Shapes.create(new AABB(0.0625d,0.125d,0d,0.9375d,0.8125d,0.4375d));
            case SOUTH ->Shapes.create(new AABB(0.0625d,0.125d,0.5625d,0.9375d,0.8125d,1d));
            case EAST ->Shapes.create(new AABB(0.5625d,0.125d,0.9375d,1d,0.8125d,0.0625d));
            case WEST ->Shapes.create(new AABB(0.5625d,0.125d,0.0625d,0d,0.8125d,.9375d));
            default -> Shapes.create(new AABB(0d,0d,0d,0d,0d,0d));
        };
    }
}
