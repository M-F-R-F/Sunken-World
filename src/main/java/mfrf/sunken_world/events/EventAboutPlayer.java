package mfrf.sunken_world.events;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.helper.Tools;
import mfrf.sunken_world.registry.Attributes;
import mfrf.sunken_world.registry.DamageSources;
import mfrf.sunken_world.registry.Dimensions;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.OptionalDouble;

public class EventAboutPlayer {

    @SubscribeEvent
    public void onDig(net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event) {
        if (event.getPlayer().isEyeInFluid(FluidTags.WATER)) {
            OptionalDouble attributeModifiers = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND).getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.SWIFT_DIG.get()).stream().mapToDouble(AttributeModifier::getAmount).max();
            attributeModifiers.ifPresent(value -> event.setNewSpeed((float) (event.getNewSpeed() * value)));
        }
    }

    @SubscribeEvent
    public void onTick(net.minecraftforge.event.TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        Level level = player.level;
        if (Config.getDimensionsWillBeEffect().contains(level.dimension().location().toString())) {
            int blockY = player.getBlockY();
            if (Config.ENABLE_FURY_OF_SKY.get() && blockY <= Config.SEA_LEVEL_BOTTOM.get() && player.getAttribute(Attributes.GRACE_OF_STRATA.get()) == null) {
                player.hurt(DamageSources.FURY_OF_STRATA, Config.DAMAGE_FURY_OF_STRATA.get().floatValue());
            }
            if (Config.ENABLE_FURY_OF_SKY.get() && blockY >= Config.SEA_LEVEL_TOP.get() && player.getAttribute(Attributes.GRACE_OF_SKY.get()) == null) {
                player.hurt(DamageSources.FURY_OF_SKY, Config.DAMAGE_FURY_OF_SKY.get().floatValue());
            }
        }
    }


    @SubscribeEvent
    public void onPlayerDrown(LivingDeathEvent event) {
        if (event.getSource() == DamageSource.DROWN) {
            LivingEntity entityLiving = event.getEntityLiving();
            Level level = entityLiving.getLevel();
            BlockPos onPos = entityLiving.getOnPos();
            if (level.getBiome(onPos).is(BiomeTags.IS_OCEAN)) {
                if (event.getEntityLiving().getAttribute(Attributes.DISTANCE_CALLING.get()) != null) {
                    entityLiving.heal(entityLiving.getMaxHealth());
                    entityLiving.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 256, 1, true, true));
                    Tools.teleport(entityLiving, entityLiving.getServer().getLevel(Dimensions.SUNKEN_WORLD), onPos, true);
                    event.setCanceled(true);
                }
            }
        }
    }


}
