package mfrf.sunken_world.items;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.Entities.under_water_torch.UnderWaterTorch;
import mfrf.sunken_world.Entities.water_block_projectile.WaterBlockProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemThrowableUnderWaterTorch extends Item {
    public ItemThrowableUnderWaterTorch(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            ItemStack itemInHand = player.getItemInHand(hand);
            UnderWaterTorch projectile = new UnderWaterTorch(player, level);
            projectile.getEntityData().set(UnderWaterTorch.STACK,itemInHand.copy());
            projectile.getEntityData().set(UnderWaterTorch.TIME,itemInHand.getMaxDamage() - itemInHand.getDamageValue());
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(projectile);
            itemInHand.shrink(1);
        }
        return super.use(level, player, hand);
    }


}
