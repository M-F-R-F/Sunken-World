//package mfrf.sunken_world.mixin;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(BlockBehaviour.class)
//public abstract class MixinBlockBehaviour {
//
//
//    @Inject(method = "getLightBlock", at = @At("HEAD"), cancellable = true)
//    public void getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos, CallbackInfoReturnable<Integer> cir) {
//        if (pState.getBlock() == Blocks.WATER) {
//            cir.setReturnValue(0);
//        }
//    }
//
//}
