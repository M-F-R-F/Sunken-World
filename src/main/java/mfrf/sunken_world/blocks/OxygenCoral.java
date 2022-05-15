package mfrf.sunken_world.blocks;

import mfrf.sunken_world.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class OxygenCoral extends Block {
    public OxygenCoral(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
        if(pRandom.nextInt(100) + 1 < Config.PROBABILITY_OXYGEN_CORAL_GENERATE_BUBBLE.get()) {

        }
    }
}
