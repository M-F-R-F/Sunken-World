package mfrf.sunken_world.worldgen.deco_features;

import mfrf.sunken_world.blocks.decos.HorizontalAttachBlock;
import mfrf.sunken_world.registry.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class RedCoralFeature extends Feature<NoneFeatureConfiguration> {
    public RedCoralFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> pContext) {
        WorldGenLevel worldgenlevel = pContext.level();
        BlockPos blockpos = pContext.origin();
        Random random = pContext.random();
        pContext.config();
        HashSet<BlockPos> cache = new HashSet<>();
        AtomicInteger flag = new AtomicInteger(0);
        AABB aabb = AABB.ofSize(new Vec3(blockpos.getX(), blockpos.getY(), blockpos.getZ()), random.nextInt(7) + 1, random.nextInt(7) + 1, random.nextInt(7) + 1);
        BlockPos.betweenClosedStream(aabb).forEach(pos -> {

            for (Direction direction : Direction.Plane.HORIZONTAL) {
                BlockPos relative = pos.relative(direction);
                if (random.nextInt(100) > 80) {

                    if (!cache.contains(relative)) {

                        if (worldgenlevel.isWaterAt(pos)) {
                            if (VineBlock.isAcceptableNeighbour(worldgenlevel, relative, direction)) {

                                worldgenlevel.setBlock(relative, Blocks.RED_CORAL.get().defaultBlockState().setValue(HorizontalAttachBlock.ATTACH_DIRECTION, direction), 2);
                                cache.add(pos);
                                flag.addAndGet(1);

                            }
                        }
                    }

                }
            }

        });
//        if (flag.get() > 0) {
//            Minecraft.getInstance().player.sendMessage(new TextComponent(flag.toString()), null);
//        }
        return flag.get() > 0;
    }
}
