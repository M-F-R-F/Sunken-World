package mfrf.sunken_world.items.accessories;

import mfrf.sunken_world.capabilities.oxygentank.OxygenTankImplement;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Objects;

public class OxygenTank extends CurioBase {

    private final int oxygenContain;
    private final int recoverySpeed;

    public OxygenTank(Properties pProperties, int oxygenContain, int recoverySpeed) {
        super(pProperties);
        this.oxygenContain = oxygenContain;
        this.recoverySpeed = recoverySpeed;
    }


    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext.entity();
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        OxygenTankImplement.attach(stack, oxygenContain, recoverySpeed);
        return super.initCapabilities(stack, nbt);
    }

}
