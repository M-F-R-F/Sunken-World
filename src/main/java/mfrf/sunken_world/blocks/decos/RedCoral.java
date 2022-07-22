package mfrf.sunken_world.blocks.decos;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
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
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction value = pState.getValue(HorizontalAttachBlock.ATTACH_DIRECTION);
        return switch (value){
//            case SOUTH ->
//            case NORTH ->
//            case WEST ->
//            case EAST ->
            default -> Shapes.create(new AABB(0d,0d,0d,.9d,0.9d,0.9d)); //这是如何创建一个碰撞箱 数字后加d表示这是一个包含小数位的数字
        };
    }

}
