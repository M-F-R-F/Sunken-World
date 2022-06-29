package mfrf.sunken_world.worldgen.deco_features;

import com.mojang.serialization.Codec;
import mfrf.sunken_world.Config;
import mfrf.sunken_world.blocks.decos.gloil_kelp.GloilKelpPlantBlock;
import mfrf.sunken_world.blocks.decos.gloil_kelp.GloilKelpRootBlock;
import mfrf.sunken_world.helper.Tools;
import mfrf.sunken_world.registry.Biomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

import javax.tools.Tool;
import java.util.Random;

public class GloilKelpFeature extends Feature<NoneFeatureConfiguration> {
    public GloilKelpFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        int i = 0;
        WorldGenLevel worldgenlevel = pContext.level();
        boolean isInOriginalDimension = Tools.dimContainsInList(worldgenlevel);
        BlockPos blockpos = pContext.origin();
        if (isInOriginalDimension || worldgenlevel.getBiome(blockpos).is(Biomes.MYSTERY_OCEAN)) {

            Random random = pContext.random();

            int currentY = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX(), blockpos.getZ());

            BlockPos currentPos = new BlockPos(blockpos.getX(), currentY, blockpos.getZ());

            if (worldgenlevel.getBlockState(currentPos).is(Blocks.WATER)) {

                BlockState root = mfrf.sunken_world.registry.Blocks.GLOIL_KELP_ROOT.block().get().defaultBlockState();
                BlockState body = mfrf.sunken_world.registry.Blocks.GLOIL_KELP_BODY.get().defaultBlockState();

                int k = 1 + random.nextInt(isInOriginalDimension ? 10 : 50);

                for (int l = 0; l <= k; ++l) {
                    if (worldgenlevel.getBlockState(currentPos).is(Blocks.WATER) && worldgenlevel.getBlockState(currentPos.above()).is(Blocks.WATER) && body.canSurvive(worldgenlevel, currentPos)) {
                        if (l == k) {
                            worldgenlevel.setBlock(currentPos, root.setValue(GloilKelpRootBlock.AGE, Integer.valueOf(random.nextInt(4) + 20)), 2);
                            ++i;
                        } else {
                            //30% chance
                            if (random.nextInt(100) > 30) {
                                worldgenlevel.setBlock(currentPos, body.setValue(GloilKelpPlantBlock.OIL_SEED, random.nextInt(4)), 2);
                            } else {
                                worldgenlevel.setBlock(currentPos, body, 2);
                            }
                        }
                    } else if (l > 0) {
                        BlockPos blockpos2 = currentPos.below();
                        if (root.canSurvive(worldgenlevel, blockpos2) && !worldgenlevel.getBlockState(blockpos2.below()).is(mfrf.sunken_world.registry.Blocks.GLOIL_KELP_ROOT.block().get())) {
                            worldgenlevel.setBlock(blockpos2, root.setValue(GloilKelpRootBlock.AGE, Integer.valueOf(random.nextInt(4) + 20)), 2);
                            ++i;
                        }
                        break;
                    }

                    currentPos = currentPos.above();
                }
            }

        }
        return i > 0;
    }
}
