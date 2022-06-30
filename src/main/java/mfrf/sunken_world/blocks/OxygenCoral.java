package mfrf.sunken_world.blocks;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.Entities.bubble.BubbleEntity;
import mfrf.sunken_world.registry.Entities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class OxygenCoral extends Block {
    public OxygenCoral(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        super.randomTick(pState, pLevel, pPos, pRandom);
        if (pLevel.getBlockState(pPos.above()).getBlock() == Blocks.WATER && pRandom.nextInt(100) + 1 < Config.PROBABILITY_OXYGEN_CORAL_GENERATE_BUBBLE.get()) {
//            Entity spawn = Entities.BUBBLE.get().spawn(pLevel, null, null, pPos.above(), MobSpawnType.NATURAL, true, false);
//            spawn.getEntityData().set(BubbleEntity.OXYGEN_CONTAIN, pRandom.nextInt(300) + 300);
            //todo use some math magic~
        }
    }
}
