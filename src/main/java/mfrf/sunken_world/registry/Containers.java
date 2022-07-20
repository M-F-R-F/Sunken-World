package mfrf.sunken_world.registry;

import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.gui.nether_furnace.NetherFurnaceContainer;
import mfrf.sunken_world.gui.waterproof_furnace.WaterproofFurnaceContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Containers {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SunkenWorld.MODID);

    public static final RegistryObject<MenuType<NetherFurnaceContainer>> NETHER_FURNACE = CONTAINERS.register("nether_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> new NetherFurnaceContainer(windowId, data.readBlockPos(), inv, inv.player, new SimpleContainerData(5))));
    public static final RegistryObject<MenuType<WaterproofFurnaceContainer>> WATER_PROOF_FURNACE = CONTAINERS.register("water_proof_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> new WaterproofFurnaceContainer(windowId, data.readBlockPos(), inv, inv.player, new SimpleContainerData(6))));
}
