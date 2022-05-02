package mfrf.sunken_world.items;

import mfrf.sunken_world.registry.Attributes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SwiftPaint extends Item {
    public SwiftPaint(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            ItemStack stack;
            if (pUsedHand == InteractionHand.MAIN_HAND) {
                stack = pPlayer.getItemInHand(InteractionHand.OFF_HAND);
            } else {
                stack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
            }
            stack.addAttributeModifier(Attributes.SWIFT_DIG.get(), new AttributeModifier("tool_modifier", 0.5, AttributeModifier.Operation.ADDITION),null);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

}
