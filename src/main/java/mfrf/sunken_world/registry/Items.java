package mfrf.sunken_world.registry;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.items.DebugTool;
import mfrf.sunken_world.items.SwiftPaint;
import mfrf.sunken_world.items.accessories.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SunkenWorld.MODID);
    public static final Item.Properties DEFAULT_PROPERTIES = new Item.Properties().stacksTo(64).tab(SunkenWorld.TAB);
    public static final Item.Properties NON_STACK_NON_BREAK_PROPERTIES = new Item.Properties().stacksTo(1).tab(SunkenWorld.TAB);

    //gear
    public static final RegistryObject<Item> SWIM_GLASS = ITEMS.register("swim_glass", () -> new SwimGlass(new Item.Properties().stacksTo(1).tab(SunkenWorld.TAB)));
    public static final RegistryObject<Item> OXYGEN_TANK_1 = ITEMS.register("oxygen_tank_1", () -> new OxygenTank(new Item.Properties().stacksTo(1).tab(SunkenWorld.TAB), Config.OXYGEN_TANK_CAPACITY_TIER_1.get(), Config.OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_1.get()));
    public static final RegistryObject<Item> OXYGEN_TANK_2 = ITEMS.register("oxygen_tank_2", () -> new OxygenTank(new Item.Properties().stacksTo(1).tab(SunkenWorld.TAB), Config.OXYGEN_TANK_CAPACITY_TIER_2.get(), Config.OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_2.get()));
    public static final RegistryObject<Item> OXYGEN_TANK_3 = ITEMS.register("oxygen_tank_3", () -> new OxygenTank(new Item.Properties().stacksTo(1).tab(SunkenWorld.TAB), Config.OXYGEN_TANK_CAPACITY_TIER_3.get(), Config.OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_3.get()));

    //resources
    public static final RegistryObject<Item> ADHESIVE = ITEMS.register("adhesive", () -> new Item(DEFAULT_PROPERTIES));
    public static final RegistryObject<Item> WATER_REPELLENT = ITEMS.register("water_repellent", () -> new Item(DEFAULT_PROPERTIES));
    public static final RegistryObject<Item> OXIDIZER = ITEMS.register("oxidizer", () -> new Item(DEFAULT_PROPERTIES));
    public static final RegistryObject<Item> PROTECTIVE_AGENT = ITEMS.register("protective_agent", () -> new Item(DEFAULT_PROPERTIES));
    public static final RegistryObject<Item> SWIFT_PAINT = ITEMS.register("swift_paint", () -> new SwiftPaint(DEFAULT_PROPERTIES));


    //flipper
//    public static final RegistryObject<Item> TEST_FLIPPER = ITEMS.register("test_flipper", () -> new Flipper(new Item.Properties().stacksTo(1).tab(SunkenWorld.TAB)));
    public static final RegistryObject<Item> FLIPPER = ITEMS.register("flipper", () -> new Flipper(NON_STACK_NON_BREAK_PROPERTIES, Config.FLIPPER_SPEED_BOOST.get()));
    public static final RegistryObject<Item> JET_FLIPPER = ITEMS.register("jet_flipper", () -> new JetFlipper(NON_STACK_NON_BREAK_PROPERTIES, Config.JET_FLIPPER_SPEED_BOOST.get()));
    public static final RegistryObject<Item> END_FLIPPER = ITEMS.register("end_flipper", () -> new EndFlipper(NON_STACK_NON_BREAK_PROPERTIES, Config.END_FLIPPER_SPEED_BOOST.get()));

    //technical
    public static final RegistryObject<Item> DEBUG_TOOL = ITEMS.register("debug_tool", DebugTool::new);
}
