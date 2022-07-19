package mfrf.sunken_world.mixin;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.registry.Dimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Drowned.class)
public class MixinDrowned {

    @Inject(method = "checkDrownedSpawnRules", at = @At(value = "RETURN",ordinal = 2),cancellable = true,remap = false)
    private static void checkDrownedSpawnRules(EntityType<Drowned> p_32350_, ServerLevelAccessor p_32351_, MobSpawnType p_32352_, BlockPos p_32353_, Random p_32354_, CallbackInfoReturnable<Boolean> cir) {
        if (p_32351_.getLevel().dimension().location() == Dimensions.SUNKEN_WORLD.location()) {
            boolean flag = p_32351_.getDifficulty() != Difficulty.PEACEFUL && Drowned.isDarkEnoughToSpawn(p_32351_, p_32353_, p_32354_) && (p_32352_ == MobSpawnType.SPAWNER || p_32351_.getFluidState(p_32353_).is(FluidTags.WATER));
            cir.setReturnValue(flag && p_32353_.getY() < Config.SEA_LEVEL_BOTTOM.get() && p_32354_.nextInt(30) == 0);
        }
    }

}
