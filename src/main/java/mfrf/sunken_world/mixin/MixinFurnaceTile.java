package mfrf.sunken_world.mixin;

import mfrf.sunken_world.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public class MixinFurnaceTile {


    @Inject(method = "serverTick", at = @At("HEAD"), cancellable = true)
    private static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, AbstractFurnaceBlockEntity pBlockEntity, CallbackInfo ci) {
        if (!Config.CAN_FURNACE_BURN_UNDER_WATER.get() && !pLevel.getBlockState(pPos.relative(pState.getValue(HorizontalDirectionalBlock.FACING))).isAir()) {
            ci.cancel();
        }
        //todo add moisture proof furnace check
    }

}
