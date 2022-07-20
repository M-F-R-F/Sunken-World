package mfrf.sunken_world.gui.waterproof_furnace;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import mfrf.sunken_world.blocks.nether_furnace.TileNetherFurnace;
import mfrf.sunken_world.blocks.water_proof_furnace.TileWaterProofFurnace;
import mfrf.sunken_world.gui.ContainerUtil;
import mfrf.sunken_world.registry.Blocks;
import mfrf.sunken_world.registry.Containers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

public class WaterproofFurnaceContainer extends AbstractContainerMenu {

    private ContainerData data;
    private InvWrapper playerInventory;
    public TileWaterProofFurnace blockEntity;
    private Player playerEntity;

    public WaterproofFurnaceContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player, @NotNull ContainerData data) {
        super(Containers.WATER_PROOF_FURNACE.get(), windowId);
        blockEntity = (TileWaterProofFurnace) player.getCommandSenderWorld().getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.data = data;
        ContainerUtil.layoutPlayerInventorySlots(8, 84, this::addSlot, this.playerInventory);

        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
                addSlot(new SlotItemHandler(iItemHandler, 0, 56, 17));
                addSlot(new SlotItemHandler(iItemHandler, 1, 56, 53) {
                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return ForgeHooks.getBurnTime(stack, null) != 0;
                    }
                });
                addSlot(new SlotItemHandler(iItemHandler, 2, 116, 35) {
                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return false;
                    }
                });
            });
        }
        addSlot(new SlotItemHandler(blockEntity.oxidizerSlot, 0, 147, 60));
        addDataSlots(this.data);
    }

    public int getBurnProgress() {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    public int getLitProgress() {
        int i = this.data.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.data.get(0) * 13 / i;
    }

    public float getPercent() {
        int i = this.data.get(4);
        int j = this.data.get(5);
        return ((float) i) / j;
    }


    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
//        return super.quickMoveStack(pPlayer, pIndex);
        return ItemStack.EMPTY;
    }


    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Blocks.WATER_PROOF_FURNACE.get());
    }


    public boolean soulFireLit() {
        return data.get(4) > 0;
    }

    public boolean isLit() {
        return this.data.get(0) > 0;
    }
}
