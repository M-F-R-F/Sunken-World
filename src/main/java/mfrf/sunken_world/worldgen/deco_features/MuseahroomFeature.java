package mfrf.sunken_world.worldgen.deco_features;

import com.mojang.serialization.Codec;
import mfrf.sunken_world.helper.Tools;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;

import javax.tools.Tool;
import java.util.Random;

public class MuseahroomFeature extends Feature<CountConfiguration> {
    public MuseahroomFeature() {
        super(CountConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<CountConfiguration> p_160316_) {
        Random random = p_160316_.random();
        WorldGenLevel level = p_160316_.level();
        BlockPos blockpos = p_160316_.origin();
        if (Tools.inListOrInMysteryOcean(level, blockpos)) {
            int count = p_160316_.config().count().sample(random);
            if (Tools.dimContainsInList(level)) {
                count *= 1.5;
            }
            int i = 0;

            for (int k = 0; k < count; ++k) {
                int l = random.nextInt(8) - random.nextInt(8);
                int i1 = random.nextInt(8) - random.nextInt(8);
                int j1 = level.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX() + l, blockpos.getZ() + i1);
                BlockPos blockpos1 = new BlockPos(blockpos.getX() + l, j1, blockpos.getZ() + i1);
                BlockState blockstate = mfrf.sunken_world.registry.Blocks.MUSEAHROOM.block().get().defaultBlockState();
                if (level.getBlockState(blockpos1).is(Blocks.WATER) && blockstate.canSurvive(level, blockpos1)) {
                    level.setBlock(blockpos1, blockstate, 2);
                    ++i;
                }
            }

            return i > 0;
        }
        return false;
    }
}
