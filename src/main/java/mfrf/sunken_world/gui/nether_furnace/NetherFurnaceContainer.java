package mfrf.sunken_world.gui.nether_furnace;

import mfrf.sunken_world.blocks.nether_furnace.TileNetherFurnace;
import mfrf.sunken_world.gui.ContainerUtil;
import mfrf.sunken_world.registry.Blocks;
import mfrf.sunken_world.registry.Containers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NetherFurnaceContainer extends AbstractContainerMenu {

    private ContainerData data;
    private InvWrapper playerInventory;
    public TileNetherFurnace blockEntity;
    private Level level;
    private Player playerEntity;

    public NetherFurnaceContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player, @NotNull ContainerData data) {
        super(Containers.NETHER_FURNACE.get(), windowId);
        blockEntity = (TileNetherFurnace) player.getCommandSenderWorld().getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.level = player.getCommandSenderWorld();
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
        addDataSlots(this.data);
    }


    public int getBurnProgress() {
        int i = data.get(2);
        int j = data.get(3);
        return 34 - ((i == 0 || j == 0) ? 18 : (18 * i / j));
    }

    public int getLitProgress() {
        int max = data.get(1);
        int current = data.get(0);
        if (max != 0) {
            return 16 * current / max;
        }

        return 0;

    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
//        return super.quickMoveStack(pPlayer, pIndex);
        return ItemStack.EMPTY;
    }


    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Blocks.NETHER_FURNACE.block().get());
    }


    public boolean soulFireLit() {
        return data.get(4) > 0;
    }
}
