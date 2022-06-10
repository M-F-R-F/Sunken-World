package mfrf.sunken_world.items.accessories;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.helper.CurioHelper;
import mfrf.sunken_world.registry.Capabilities;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import top.theillusivec4.curios.api.SlotContext;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class JetFlipper extends Flipper {

    public JetFlipper(Properties pProperties, Double speedBoost) {
        super(pProperties, speedBoost);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        super.curioTick(slotContext, stack);
        if (JetBoostActivated(stack)) {
            LivingEntity entity = slotContext.entity();
            Vec3 deltaMovement = entity.getDeltaMovement();
            Vec3 vec3 = new Vec3(deltaMovement.x, deltaMovement.y, deltaMovement.z);
            vec3.scale(1 / vec3.length());
            entity.move(MoverType.SELF, vec3.scale(Config.JET_FLIPPER_SPEED_ACTIVATED_BOOST.get()));

            if (!costOxygen(entity)) {
                stack.getOrCreateTag().putInt("jet_boost_activated", 1);
//                Level level = entity.level;
//                double xMov = entity.getX() - entity.xOld;
//                double yMov = entity.getY() - entity.yOld;
//                double zMov = entity.getZ() - entity.zOld;
//                level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE, entity.getX(), entity.getY(), entity.getZ(), xMov, yMov, zMov);
            }
        }
    }

    private boolean JetBoostActivated(ItemStack stack) {
        CompoundTag orCreateTag = stack.getOrCreateTag();
        if (orCreateTag.contains("jet_boost_activated")) {
            return orCreateTag.getInt("jet_boost_activated") == 2;
        } else {
            orCreateTag.putInt("jet_boost_activated", 0);
        }
        return false;
    }

    public static void switchJetBoost(ItemStack stack) {
        CompoundTag orCreateTag = stack.getOrCreateTag();
        if (orCreateTag.getInt("jet_boost_activated") >= 1) {
            orCreateTag.putInt("jet_boost_activated", 0);
        } else {
            orCreateTag.putInt("jet_boost_activated", 2);
        }
    }

    private boolean costOxygen(LivingEntity entity) {
        AtomicInteger cost = new AtomicInteger(Config.JET_FLIPPER_COST_ON_ACTIVATED_PER_TICK.get());
        AtomicBoolean complete = new AtomicBoolean(false);

        CurioHelper.findAnyItem(entity, "oxygen_tank", itemStack -> {
            itemStack.getCapability(Capabilities.OXYGEN_PROVIDER_ITEM_CAP_CAPABILITY).ifPresent(oxygenProvider -> {
                complete.set(cost.addAndGet(-oxygenProvider.extractOxygen(cost.get())) == 0);
            });
            return complete.get();
        });

        return complete.get();
    }
}
