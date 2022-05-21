package mfrf.sunken_world.mixin;

import mfrf.sunken_world.registry.Attributes;
import mfrf.sunken_world.registry.Capabilities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

@Mixin(Entity.class)
public abstract class MixinEntity {

    @Shadow(aliases = "this$0")
    private Entity inst;

    @Inject(method = "getAirSupply", at = @At("RETURN"), cancellable = true)
    public void getAirSupply(CallbackInfoReturnable<Integer> info) {
        executeIFExistAdditionOxygenCapacityAttribute((livingEntity, attributeInstance) -> {
            livingEntity.getCapability(CuriosCapability.INVENTORY).ifPresent(inventory -> {
                inventory.getStacksHandler("oxygen_tank").ifPresent(stack -> {
                    IDynamicStackHandler stacks = stack.getStacks();
                    AtomicInteger airInTank = new AtomicInteger();
                    for (int i = 0; i < stacks.getSlots(); i++) {
                        stacks.getStackInSlot(i).getCapability(Capabilities.OXYGEN_PROVIDER_ITEM_CAP_CAPABILITY).ifPresent(oxygenProviderItemCapability -> {
                            airInTank.addAndGet(oxygenProviderItemCapability.getOxygen());
                        });
                    }
                    info.setReturnValue(info.getReturnValue() + airInTank.get());
                });
            });
        });
    }

    @Inject(method = "setAirSupply", at = @At("HEAD"), cancellable = true)
    public void setAirSupply(int pAir, CallbackInfo ci) {
        if (pAir > inst.getAirSupply()) {
            int remain = pAir - inst.getMaxAirSupply();
            if (remain > 0) {
                inst.setAirSupply(inst.getMaxAirSupply());
                executeIFExistAdditionOxygenCapacityAttribute((livingEntity, attributeInstance) -> {
                    livingEntity.getCapability(CuriosCapability.INVENTORY).ifPresent(inventory -> {
                        inventory.getStacksHandler("oxygen_tank").ifPresent(stack -> {
                            IDynamicStackHandler stacks = stack.getStacks();
                            AtomicInteger remain2 = new AtomicInteger(-1);

                            for (int i = 0; i < stacks.getSlots(); i++) {

                                if (remain2.get() != 0) {
                                    stacks.getStackInSlot(i).getCapability(Capabilities.OXYGEN_PROVIDER_ITEM_CAP_CAPABILITY).ifPresent(oxygenProviderItemCapability -> {
                                        remain2.set(oxygenProviderItemCapability.addOxygen(oxygenProviderItemCapability.getOxygen() + remain));
                                    });
                                } else {
                                    break;
                                }

                            }


                        });
                    });
                });
            }
        }

    }

    private void executeIFExistAdditionOxygenCapacityAttribute(BiConsumer<LivingEntity, AttributeInstance> consumer) {
        if (inst instanceof LivingEntity livingEntity) {
            AttributeInstance attribute = livingEntity.getAttribute(Attributes.ADDITION_OXYGEN_CAPACITY.get());
            if (attribute != null) {
                consumer.accept(livingEntity, attribute);
            }
        }
    }
}


