package mfrf.sunken_world.mixin;

import mfrf.sunken_world.registry.Items;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.concurrent.atomic.AtomicBoolean;

@Mixin(FogRenderer.class)
public class MixinFogRender {

    //copy from https://github.com/Snownee/LavaClearView/blob/1.18-forge/src/main/java/snownee/clearview/mixin/MixinFogRenderer.java
    @Redirect(
            method = "setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;FZF)V", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;isSpectator()Z", ordinal = 0, remap = true
    ), remap = false
    )
    private static boolean clearview$setupFog(Entity entity) {
        if (entity instanceof Player player) {
            //todo change inject point
            if (player.isEyeInFluid(FluidTags.WATER)) {
                AtomicBoolean flag = new AtomicBoolean(false);
                player.getCapability(CuriosCapability.INVENTORY).ifPresent(iCuriosItemHandler -> {
                    iCuriosItemHandler.getStacksHandler("head").ifPresent(iCurioStacksHandler -> {
                        IDynamicStackHandler stacks = iCurioStacksHandler.getStacks();
                        for (int i = 0; i < stacks.getSlots(); i++) {
                            if (stacks.getStackInSlot(i).getItem() == Items.SWIM_GLASS.get()) {
                                flag.set(true);
                                break;
                            }
                        }
                    });
                });
                if (flag.get()) {
                    return true;
                }
            }
        }
        return entity.isSpectator();
    }
}
