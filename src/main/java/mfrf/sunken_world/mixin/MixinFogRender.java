package mfrf.sunken_world.mixin;

import mfrf.sunken_world.Config;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FogType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FogRenderer.class)
public class MixinFogRender {

    @Redirect(
            method = "setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;FZF)V", at = @At(
            value = "INVOKE",target = "Lnet/minecraft/client/Camera;getFluidInCamera()Lnet/minecraft/world/level/material/FogType;"
    ), remap = false
    )
    private static FogType modifyType$setupFog(Camera instance) {
        FogType fluidInCamera = instance.getFluidInCamera();
        if(fluidInCamera == FogType.WATER && Config.DISABLE_WATER_FOG.get()){
            return FogType.NONE;
        }
        return fluidInCamera;
    }

}
