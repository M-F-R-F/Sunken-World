package mfrf.sunken_world.items.accessories;

import com.google.common.collect.Multimap;
import mfrf.sunken_world.capabilities.oxygentank.IOxygenProviderItemCap;
import mfrf.sunken_world.capabilities.oxygentank.OxygenTankImplement;
import mfrf.sunken_world.network.sync_player_info.PacketSyncOxygenTank;
import mfrf.sunken_world.network.sync_player_info.SyncPlayerInfoChannel;
import mfrf.sunken_world.registry.Attributes;
import mfrf.sunken_world.registry.Capabilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.UUID;

public class OxygenTank extends CurioBase {

    private final int oxygenCapacity;
    private final int recoverySpeed;

    public OxygenTank(Properties pProperties, int oxygenCapacity, int recoverySpeed) {
        super(pProperties);
        this.oxygenCapacity = oxygenCapacity;
        this.recoverySpeed = recoverySpeed;
    }


    @Override
    public void onCraftedBy(ItemStack pStack, Level pLevel, Player pPlayer) {
        super.onCraftedBy(pStack, pLevel, pPlayer);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        stack.getCapability(Capabilities.OXYGEN_PROVIDER_ITEM_CAP_CAPABILITY).ifPresent(oxygenTank -> {
            LivingEntity entity = slotContext.entity();
            int airSupply = entity.getAirSupply();
            int maxAirSupply = entity.getMaxAirSupply();
            if (entity.getAirSupply() == 0) {
                if (!oxygenTank.isEmpty()) {
                    entity.setAirSupply(oxygenTank.extractOxygen(maxAirSupply - 1));
                    syncPlayerInfo((float) oxygenTank.getOxygen() / oxygenTank.getCapacity(), entity);
                }
            }

            if (oxygenTank.getOxygen() < oxygenTank.getCapacity() && (!entity.isEyeInFluid(FluidTags.WATER) || entity.level.getBlockState(entity.blockPosition()).is(Blocks.BUBBLE_COLUMN))) {
                oxygenTank.recovery();
                syncPlayerInfo((float) oxygenTank.getOxygen() / oxygenTank.getCapacity(), entity);
            }
        });
    }

    private void syncPlayerInfo(float percent, LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            SyncPlayerInfoChannel.sendToClient(new PacketSyncOxygenTank(percent), player);
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        super.onEquip(slotContext, prevStack, stack);
        stack.getCapability(Capabilities.OXYGEN_PROVIDER_ITEM_CAP_CAPABILITY).ifPresent(oxygenTank -> {
            syncPlayerInfo((float) oxygenTank.getOxygen() / oxygenTank.getCapacity(), slotContext.entity());
        });
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        super.onUnequip(slotContext, newStack, stack);
        syncPlayerInfo(-1, slotContext.entity());
    }


    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new OxygenTankImplement(oxygenCapacity, recoverySpeed, stack);
    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> attributeModifiers = super.getAttributeModifiers(slotContext, uuid, stack);
//        attributeModifiers.put(Attributes.ADDITION_OXYGEN_CAPACITY.get(), new AttributeModifier(uuid, "addition_oxygen_capacity", oxygenCapacity, AttributeModifier.Operation.ADDITION));
//        return attributeModifiers;
//    }
}
