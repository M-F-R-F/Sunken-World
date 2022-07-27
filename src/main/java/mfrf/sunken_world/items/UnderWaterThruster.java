package mfrf.sunken_world.items;

import mfrf.sunken_world.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnderWaterThruster extends Item {
    public UnderWaterThruster(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        super.onUsingTick(stack, player, count);
        player.setSwimming(true);
        player.setDeltaMovement(player.getLookAngle().scale(Config.UNDERWATER_THRUSTER_SPEED.get()));
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return Integer.MAX_VALUE;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pUsedHand);
        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.success(itemInHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("lucent_not_loaded"));
    }

}
