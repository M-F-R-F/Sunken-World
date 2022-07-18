package mfrf.sunken_world.items;

import mfrf.sunken_world.Config;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class UnderWaterThruster extends Item {
    public UnderWaterThruster(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
        if(player.isSwimming()){
            player.setDeltaMovement(player.getLookAngle().scale(Config.UNDERWATER_THRUSTER_SPEED.get()));
        }
    }


}
