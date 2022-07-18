package mfrf.sunken_world.registry;

import mfrf.sunken_world.Config;
import mfrf.sunken_world.SunkenWorld;
import mfrf.sunken_world.items.*;
import mfrf.sunken_world.items.accessories.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SunkenWorld.MODID);
    public static final Item.Properties DEFAULT_PROPERTIES = new Item.Properties().stacksTo(64).tab(ModTab.TAB);
    public static final Item.Properties NON_STACK_NON_BREAK_PROPERTIES = new Item.Properties().stacksTo(1).tab(ModTab.TAB);

    //gear
    public static final RegistryObject<Item>
            SWIM_GLASS = ITEMS.register("swim_glass", () -> new SwimGlass(new Item.Properties().stacksTo(1).tab(ModTab.TAB))),
            OXYGEN_TANK_1 = ITEMS.register("oxygen_tank", () -> new OxygenTank(new Item.Properties().stacksTo(1).tab(ModTab.TAB), Config.OXYGEN_TANK_CAPACITY_TIER_1.get(), Config.OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_1.get())),
            OXYGEN_TANK_2 = ITEMS.register("high_pressure_oxygen_tank", () -> new OxygenTank(new Item.Properties().stacksTo(1).tab(ModTab.TAB), Config.OXYGEN_TANK_CAPACITY_TIER_2.get(), Config.OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_2.get())),
            OXYGEN_TANK_3 = ITEMS.register("extreme_high_pressure_oxygen_tank", () -> new OxygenTank(new Item.Properties().stacksTo(1).tab(ModTab.TAB), Config.OXYGEN_TANK_CAPACITY_TIER_3.get(), Config.OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_3.get())),
            FLIPPER = ITEMS.register("flipper", () -> new Flipper(NON_STACK_NON_BREAK_PROPERTIES, Config.FLIPPER_SPEED_BOOST.get())),
            JET_FLIPPER = ITEMS.register("jet_flipper", () -> new JetFlipper(NON_STACK_NON_BREAK_PROPERTIES, Config.JET_FLIPPER_SPEED_BOOST.get())),
            END_FLIPPER = ITEMS.register("end_flipper", () -> new EndFlipper(NON_STACK_NON_BREAK_PROPERTIES, Config.END_FLIPPER_SPEED_BOOST.get())),
            GRACE_OF_OCEAN_PENDANT_BROKEN = ITEMS.register("grace_of_ocean_pendant_broken", () -> new GracePendant(NON_STACK_NON_BREAK_PROPERTIES)),
            GRACE_OF_OCEAN_PENDANT = ITEMS.register("grace_of_ocean_pendant", () -> new GracePendant(NON_STACK_NON_BREAK_PROPERTIES)),
            GRACE_OF_SKY_PENDANT = ITEMS.register("grace_of_sky_pendant", () -> new GracePendant(NON_STACK_NON_BREAK_PROPERTIES)),
            GRACE_OF_STRATA_PENDANT = ITEMS.register("grace_of_strata_pendant", () -> new GracePendant(NON_STACK_NON_BREAK_PROPERTIES)),
            CONDUIT_PENDANT = ITEMS.register("conduit_pendant", () -> new ConduitPendant(NON_STACK_NON_BREAK_PROPERTIES)),
            SPONGE_ON_A_STICK = ITEMS.register("sponge_on_a_stick", () -> new ItemSpongeOnAStick(NON_STACK_NON_BREAK_PROPERTIES)),
            UNDERWATER_THRUSTER = ITEMS.register("underwater_thruster", () -> new UnderWaterThruster(NON_STACK_NON_BREAK_PROPERTIES));

    //resources
    public static final RegistryObject<Item>
            OXIDIZER = ITEMS.register("oxidizer", () -> new Item(DEFAULT_PROPERTIES)),
            WATER_PROOF_PAINT = ITEMS.register("water_proof_paint", () -> new WaterProofPaint(DEFAULT_PROPERTIES)),
            RED_CORAL_FRAGMENT = ITEMS.register("red_coral_fragment", () -> new Item(DEFAULT_PROPERTIES));


    //technical
    public static final RegistryObject<Item>
            DEBUG_TOOL = ITEMS.register("debug_tool", DebugTool::new);

    //place holder
    public static final RegistryObject<Item>
            THROWABLE_UNDERWATER_TORCH = ITEMS.register("under_water_torch", () -> new ItemThrowableUnderWaterTorch(new Item.Properties().tab(ModTab.TAB).durability(Config.UNDERWATER_TORCH_LIT_DURATION.get()))),
            THROWABLE_SPONGE = ITEMS.register("throwable_sponge", () -> new Item(DEFAULT_PROPERTIES));
}
