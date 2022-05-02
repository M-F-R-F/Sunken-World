package mfrf.sunken_world.items.accessories;

import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class SwimGlass extends CurioBase {
    public SwimGlass(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity.isEyeInFluid(FluidTags.WATER)) {
            entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 5, 1, false, false));
        }
    }


    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
        if (entity.hasEffect(MobEffects.NIGHT_VISION)) {
            int duration = entity.getEffect(MobEffects.NIGHT_VISION).getDuration();
            entity.removeEffect(MobEffects.NIGHT_VISION);
            if (duration >= 5) {
                entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, duration - 5, 1, false, false));
            }
        }
    }

}
