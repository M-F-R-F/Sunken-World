package mfrf.sunken_world.items.accessories;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;

public class ConduitPendant extends CurioBase {
    public ConduitPendant(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        entity.removeEffect(MobEffects.CONDUIT_POWER);
        entity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 10, 0, true, false));
    }
}
