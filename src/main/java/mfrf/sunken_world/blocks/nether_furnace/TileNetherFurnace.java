package mfrf.sunken_world.blocks.nether_furnace;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.registry.BlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileNetherFurnace extends AbstractFurnaceBlockEntity {
    private boolean nether_fire_lit;
    private int nether_fire_burned_time;

    public TileNetherFurnace(BlockPos pPos, BlockState pState) {
        super(BlockEntities.WATER_PROOF_FURNACE_TILE.get(), pPos, pState, RecipeType.SMELTING);
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

    @Override
    protected AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory) {
        return null;
        //todo create gui to furnaces
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, TileNetherFurnace pBlockEntity) {
        AbstractFurnaceBlockEntity.serverTick(pLevel, pPos, pState, pBlockEntity);
        if (pBlockEntity.isOverClockBurning()) {
            pBlockEntity.cookingProgress = Math.min(0, pBlockEntity.cookingProgress - Config.NETHER_FURNACE_OVERCLOCK_SMELTING_BOOST.get());
        }
        pBlockEntity.burnFuel();
    }

    @Override
    protected boolean isLit() {
        return super.isLit() || this.nether_fire_lit;
    }

    protected boolean isOverClockBurning() {
        return super.isLit() && this.nether_fire_lit;
    }

    public void burnFuel() {
        if (!this.isOverClockBurning()) {
            if (nether_fire_lit) {
                if (nether_fire_burned_time > Config.MIN_BURNING_TICK_OF_NETHER_FURNACE.get() && level.random.nextDouble() <= Config.PROBABILITY_NETHER_FURNACE_EXTINGUISH.get()) {
                    nether_fire_lit = false;
                    nether_fire_burned_time = 0;
                }
                nether_fire_burned_time++;
            }
        } else {
            nether_fire_lit = true;
            nether_fire_burned_time = 0;
        }
        setChanged();
    }


}
