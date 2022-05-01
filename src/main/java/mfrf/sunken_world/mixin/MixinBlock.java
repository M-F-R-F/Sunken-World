//package mfrf.sunken_world.mixin;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockState;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(Block.class)
//public class MixinBlock {
//
//    @Inject(method = "propagatesSkylightDown", at = @At("HEAD"), cancellable = true)
//    public void propagatesSkylightDown(BlockState pState, BlockGetter pLevel, BlockPos pPos, CallbackInfoReturnable<Boolean> cir) {
//        if (pState.getBlock() == Blocks.WATER) {
//            cir.setReturnValue(true);
//        }
//    }
//}
