package mfrf.sunken_world.mixin;

import mfrf.sunken_world.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;
import java.util.stream.Stream;

@Mixin(ChunkGenerator.class)
public abstract class MixinChunkGenerator {

    @Shadow
    public abstract Stream<Holder<StructureSet>> possibleStructureSets();

    @Inject(
            method = "applyBiomeDecoration",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/WorldGenLevel;setCurrentlyGenerating(Ljava/util/function/Supplier;)V",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    public void generateFeatures(WorldGenLevel pLevel, ChunkAccess pChunk, StructureFeatureManager pStructureFeatureManager, CallbackInfo ci) {
        if (Config.getDimensionsWillBeEffect().contains(pLevel.getLevel().dimension().location().toString())) {
            int top = Config.SEA_LEVEL_TOP.get();
            int bottom = Config.SEA_LEVEL_BOTTOM.get();
            ChunkPos pos = pChunk.getPos();
            Random random = pLevel.getRandom();
            for (int x = pos.getMinBlockX(); x <= pos.getMaxBlockX(); x++) {
                for (int z = pos.getMinBlockZ(); z <= pos.getMaxBlockZ(); z++) {
                    for (int y = bottom; y <= top; y++) {
                        BlockPos posToBeReplaced = new BlockPos(x, y, z);
                        BlockState blockState = pChunk.getBlockState(posToBeReplaced);
                        if (blockState.isAir()) {
                            pChunk.setBlockState(posToBeReplaced, Blocks.WATER.defaultBlockState(), false);
                        } else if (blockState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                            pChunk.setBlockState(posToBeReplaced, blockState.setValue(BlockStateProperties.WATERLOGGED, true), false);
                        } else if (blockState.getBlock() == Blocks.GRASS) {
                            pChunk.setBlockState(posToBeReplaced, Blocks.SEAGRASS.defaultBlockState(), false);
                        } else if (blockState.getBlock() == Blocks.TALL_GRASS) {
                            pChunk.setBlockState(posToBeReplaced, Blocks.TALL_SEAGRASS.defaultBlockState(), false);
                        } else if (blockState.getBlock() instanceof FlowerBlock) {
                            pChunk.setBlockState(posToBeReplaced, Blocks.SEA_PICKLE.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true).setValue(BlockStateProperties.PICKLES, random.nextInt(1, 5)), false);
                        } else if (blockState.getBlock() instanceof DoublePlantBlock) {
                            pChunk.setBlockState(posToBeReplaced, Blocks.KELP_PLANT.defaultBlockState(), false);
                        } else if (blockState.canBeReplaced(Fluids.WATER)) {
                            pChunk.setBlockState(posToBeReplaced, Blocks.WATER.defaultBlockState(), false);
                        }
                    }
                }
            }
        }
    }
}
