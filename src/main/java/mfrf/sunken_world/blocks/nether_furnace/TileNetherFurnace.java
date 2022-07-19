package mfrf.sunken_world.blocks.nether_furnace;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.gui.nether_furnace.NetherFurnaceContainer;
import mfrf.sunken_world.registry.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.commands.data.DataAccessor;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class TileNetherFurnace extends AbstractFurnaceBlockEntity {
    boolean nether_fire_lit;
    int nether_fire_burned_time;
    private ContainerData data = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return pIndex == 4 ? nether_fire_burned_time : TileNetherFurnace.super.dataAccess.get(pIndex);
        }

        @Override
        public void set(int pIndex, int pValue) {
            if (pIndex == 4) {
                nether_fire_burned_time = pValue;
                setChanged();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    };


    public TileNetherFurnace(BlockPos pPos, BlockState pState) {
        super(BlockEntities.NETHER_FURNACE_TILE.get(), pPos, pState, RecipeType.SMELTING);
    }

    @Override
    public void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putBoolean("nether_fire_lit", nether_fire_lit);
        pTag.putInt("nether_fire_burned_time", nether_fire_burned_time);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        nether_fire_lit = pTag.getBoolean("nether_fire_lit");
        nether_fire_burned_time = pTag.getInt("nether_fire_burned_time");
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("nether_furnace");
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, TileNetherFurnace pBlockEntity) {
        if (pBlockEntity.litTime < 0) {
            pBlockEntity.litTime = 0;
            pBlockEntity.setChanged();
        }
        if (pBlockEntity.cookingProgress > pBlockEntity.cookingTotalTime) {
            pBlockEntity.cookingProgress = pBlockEntity.cookingTotalTime - 1;
            pBlockEntity.litTime += 1;
            pBlockEntity.setChanged();
        }
        AbstractFurnaceBlockEntity.serverTick(pLevel, pPos, pState, pBlockEntity);
        if (pBlockEntity.isOverClockBurning()) {
            pBlockEntity.cookingProgress = pBlockEntity.cookingProgress + Config.NETHER_FURNACE_OVERCLOCK_SMELTING_BOOST.get();
            pBlockEntity.setChanged();
        }else {
            pBlockEntity.tryOverclock();
        }
        if (pBlockEntity.isLit()) {
            pBlockEntity.burnFuel();
        }

    }

    protected void tryOverclock(){
        if(!super.isLit() && canBurn()){
            ItemStack itemstack = items.get(1);
            litTime = getBurnDuration(itemstack);
            litDuration = litTime;
            if (isLit()) {
                setChanged();
                if (itemstack.hasContainerItem())
                    items.set(1, itemstack.getContainerItem());
                else
                if (!itemstack.isEmpty()) {
                    Item item = itemstack.getItem();
                    itemstack.shrink(1);
                    if (itemstack.isEmpty()) {
                        items.set(1, itemstack.getContainerItem());
                    }
                }
            }
        }
    }


    private boolean canBurn() {
        Recipe<?> recipe = level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, this, level).orElse(null);
        if (!items.get(0).isEmpty() && recipe != null) {
            ItemStack itemstack = ((Recipe<WorldlyContainer>) recipe).assemble(this);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = items.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!itemstack1.sameItem(itemstack)) {
                    return false;
                } else if (itemstack1.getCount() + itemstack.getCount() <= getMaxStackSize() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix: make furnace respect stack sizes in furnace recipes
                    return true;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        } else {
            return false;
        }
    }

    @Override
    protected boolean isLit() {
        return super.isLit() || this.nether_fire_lit;
    }

    public boolean isOverClockBurning() {
        return super.isLit() && this.nether_fire_lit;
    }

    public void burnFuel() {
        if (!this.isOverClockBurning()) {
            if (nether_fire_lit) {
                if (nether_fire_burned_time > Config.MIN_BURNING_TICK_OF_NETHER_FURNACE.get() && level.random.nextDouble() <= Config.PROBABILITY_NETHER_FURNACE_EXTINGUISH.get()) {
                    nether_fire_lit = false;
                    nether_fire_burned_time = 0;
                    setChanged();
                } else {
                    nether_fire_burned_time++;
                    setChanged();
                }
            } else if (super.isLit()) {
                nether_fire_lit = true;
                nether_fire_burned_time = 1;
                setChanged();
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("nether_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new NetherFurnaceContainer(pContainerId, getBlockPos(), pInventory, pInventory.player, data);
    }

}
