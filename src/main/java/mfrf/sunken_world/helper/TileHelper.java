package mfrf.sunken_world.helper;

import mfrf.sunken_world.registry.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileHelper {

    public static boolean isFacingAir(Level level, BlockPos pos, BlockState blockState) {
        return level.getBlockState(pos.relative(blockState.getValue(HorizontalDirectionalBlock.FACING))).isAir();
    }
}
