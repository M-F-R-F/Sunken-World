//package mfrf.sunken_world.mixin;
//
//import mfrf.sunken_world.Entities.technical.under_water_torch.UnderWaterTorch;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.level.BlockGetter;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.state.BlockBehaviour;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.AABB;
//import net.minecraft.world.phys.Vec3;
//import net.minecraftforge.common.extensions.IForgeBlockState;
//import org.spongepowered.asm.mixin.Implements;
//import org.spongepowered.asm.mixin.Interface;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.Inject;
//
//import java.util.function.ToIntFunction;
//
//@Mixin(value = BlockBehaviour.Properties.class)
//public abstract class MixinBlockState{
//
//
////    public int under_water_torch$getLightEmission(BlockGetter level, BlockPos pos) {
////        if (level instanceof Level world) {
////            if (world.getEntitiesOfClass(UnderWaterTorch.class, AABB.ofSize(new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5), 1, 1, 1), underWaterTorch -> !underWaterTorch.isRemoved() && underWaterTorch.isStopped()).isEmpty()) {
////                return 15;
////            }
////        }
////        return IForgeBlockState.super.getLightEmission(level, pos);
////    }
//}
