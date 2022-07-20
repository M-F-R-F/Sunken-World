package mfrf.sunken_world.blocks.water_proof_furnace;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.gui.nether_furnace.NetherFurnaceContainer;
import mfrf.sunken_world.gui.waterproof_furnace.WaterProofFurnaceScreen;
import mfrf.sunken_world.gui.waterproof_furnace.WaterproofFurnaceContainer;
import mfrf.sunken_world.helper.TileHelper;
import mfrf.sunken_world.registry.BlockEntities;
import mfrf.sunken_world.registry.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TileWaterProofFurnace extends AbstractFurnaceBlockEntity {
    public ItemStackHandler oxidizerSlot = new ItemStackHandler(1) {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.is(Items.OXIDIZER.get());
        }
    };
    protected int oxidizerRemain;
    protected int maxOxidizer = Config.OXIDIZER_LAST_TIME.get();
    public ContainerData data = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return switch (pIndex) {
                case 4 -> oxidizerRemain;
                case 5 -> maxOxidizer;
                default -> TileWaterProofFurnace.super.dataAccess.get(pIndex);
            };
        }

        @Override
        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 4 -> {
                    oxidizerRemain = pValue;
                }
                case 5 -> {
                    maxOxidizer = pValue;
                }
                default -> TileWaterProofFurnace.super.dataAccess.set(pIndex, pValue);
            }
        }

        @Override
        public int getCount() {
            return 6;
        }
    };

    public TileWaterProofFurnace(BlockPos pPos, BlockState pState) {
        super(BlockEntities.WATER_PROOF_FURNACE_TILE.get(), pPos, pState, RecipeType.SMELTING);
    }

    @Override
    public void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putInt("oxidizer_remain", oxidizerRemain);
        pTag.put("oxidizer_slot", oxidizerSlot.serializeNBT());
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        oxidizerRemain = pTag.getInt("oxidizer_remain");
        oxidizerSlot.deserializeNBT(pTag.getCompound("oxidizer_slot"));
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("water_proof_furnace");
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
        if (facing == getBlockState().getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            return LazyOptional.of(() -> oxidizerSlot).cast();
        }
        return super.getCapability(capability, facing);
    }

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return new WaterproofFurnaceContainer(pContainerId, getBlockPos(), pInventory, pInventory.player, data);
    }

    public void setTag(CompoundTag tag) {
        super.load(tag);
        setChanged();
    }

    public boolean tryConsumeOxidizer() {
        if (oxidizerRemain <= 0 && oxidizerSlot.getStackInSlot(0).getItem() == Items.OXIDIZER.get()) {
            oxidizerSlot.getStackInSlot(0).shrink(1);
            oxidizerRemain = Config.OXIDIZER_LAST_TIME.get();
            setChanged();
            return true;
        }
        return false;
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, TileWaterProofFurnace pBlockEntity) {
        if (TileHelper.isFacingAir(pLevel, pPos, pState)) {
            AbstractFurnaceBlockEntity.serverTick(pLevel, pPos, pState, pBlockEntity);
        } else if (pBlockEntity.oxidizerRemain > 0 || pBlockEntity.tryConsumeOxidizer()) {
            if (pBlockEntity.isLit()) {
                pBlockEntity.oxidizerRemain -= 1;
            }
            AbstractFurnaceBlockEntity.serverTick(pLevel, pPos, pState, pBlockEntity);
            pBlockEntity.setChanged();
        }
    }
}
