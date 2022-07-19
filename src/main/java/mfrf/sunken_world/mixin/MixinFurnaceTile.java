package mfrf.sunken_world.mixin;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.blocks.nether_furnace.TileNetherFurnace;
import mfrf.sunken_world.blocks.water_proof_furnace.TileWaterProofFurnace;
import mfrf.sunken_world.helper.TileHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public class MixinFurnaceTile {


    @Inject(method = "serverTick", at = @At("HEAD"), cancellable = true,remap = false)
    private static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, AbstractFurnaceBlockEntity pBlockEntity, CallbackInfo ci) {
        if (!Config.CAN_FURNACE_BURN_UNDER_WATER.get()) {
            if ((!TileHelper.isFacingAir(pLevel, pPos, pState) && !(pBlockEntity instanceof TileWaterProofFurnace) && !(pBlockEntity instanceof TileNetherFurnace))) {
                ci.cancel();
            }
        }
    }

}
