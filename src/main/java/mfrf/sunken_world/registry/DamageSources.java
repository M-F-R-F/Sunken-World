package mfrf.sunken_world.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

public class DamageSources {
    public static final DamageSource FURY_OF_STRATA = new DamageSourceWithMessage("fury_of_strata", (entity) -> new TranslatableComponent("damage.fury_of_strata", entity.getDisplayName())).bypassArmor().setMagic().setIsFire().setScalesWithDifficulty();
    public static final DamageSource FURY_OF_SKY = new DamageSourceWithMessage("fury_of_sky", (entity) -> new TranslatableComponent("damage.fury_of_strata", entity.getDisplayName())).bypassArmor().setMagic().setIsFall().setScalesWithDifficulty();
}

class DamageSourceWithMessage extends DamageSource {
    private final Function<LivingEntity, Component> message;

    public DamageSourceWithMessage(String p_19333_, Function<LivingEntity, Component> message) {
        super(p_19333_);
        this.message = message;
    }

    @Override
    public Component getLocalizedDeathMessage(LivingEntity pLivingEntity) {
        return message.apply(pLivingEntity);
    }
}
