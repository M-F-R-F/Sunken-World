package mfrf.sunken_world.helper;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.registry.Biomes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class Tools {
    public static void teleport(LivingEntity entity, ServerLevel destination, BlockPos pos, boolean findTop) {
        entity.changeDimension(destination, new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                entity = repositionEntity.apply(false);
                int y = pos.getY();
                if (findTop) {
                    y = destination.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
                }
                entity.teleportTo(pos.getX(), y, pos.getZ());
                return entity;
            }
        });
    }

    public static boolean dimContainsInList(WorldGenLevel pLevel) {
        return Config.getDimensionsWillBeEffect().contains(pLevel.getLevel().dimension().location().toString());
    }

    public static boolean inListOrInMysteryOcean(WorldGenLevel level, BlockPos pos) {
        return dimContainsInList(level) || level.getBiome(pos).is(Biomes.MYSTERY_OCEAN);
    }

}
