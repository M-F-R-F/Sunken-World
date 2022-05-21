package mfrf.sunken_world;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static ForgeConfigSpec configSpec;

    public static final ForgeConfigSpec.IntValue SEA_LEVEL_TOP;
    public static final ForgeConfigSpec.IntValue SEA_LEVEL_BOTTOM;
    public static final ForgeConfigSpec.ConfigValue<String> DIMENSIONS_WILL_BE_EFFECT;
    public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_TANK_CAPACITY_TIER_1;
    public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_1;
    public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_TANK_CAPACITY_TIER_2;
    public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_2;
    public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_TANK_CAPACITY_TIER_3;
    public static final ForgeConfigSpec.ConfigValue<Integer> OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_3;
    public static final ForgeConfigSpec.ConfigValue<Boolean> CAN_FURNACE_BURN_UNDER_WATER;
    public static final ForgeConfigSpec.ConfigValue<Double> SWIFT_DIG_ADDITION;
    public static final ForgeConfigSpec.ConfigValue<Double> MAX_SWIFT_DIG;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_FURY_OF_SKY;
    public static final ForgeConfigSpec.ConfigValue<Double> DAMAGE_FURY_OF_SKY;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_FURY_OF_STRATA;
    public static final ForgeConfigSpec.ConfigValue<Double> DAMAGE_FURY_OF_STRATA;

    public static final ForgeConfigSpec.ConfigValue<Double> FLIPPER_SPEED_BOOST;
    public static final ForgeConfigSpec.ConfigValue<Double> JET_FLIPPER_SPEED_BOOST;
    public static final ForgeConfigSpec.ConfigValue<Double> END_FLIPPER_SPEED_BOOST;
    public static final ForgeConfigSpec.ConfigValue<Double> JET_FLIPPER_SPEED_ACTIVATED_BOOST;
    public static final ForgeConfigSpec.ConfigValue<Integer> JET_FLIPPER_COST_ON_ACTIVATED_PER_TICK;
    public static final ForgeConfigSpec.ConfigValue<Integer> END_FLIPPER_TELEPORT_DISTANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> END_FLIPPER_ENERGY_CAPACITY;
    public static final ForgeConfigSpec.ConfigValue<Integer> END_FLIPPER_TELEPORT_COST;
    public static final ForgeConfigSpec.ConfigValue<Integer> END_FLIPPER_TELEPORT_COOL_DOWN;
    public static final ForgeConfigSpec.BooleanValue OXYGEN_TANK_HUD_ENABLED;
    public static final ForgeConfigSpec.IntValue PROBABILITY_OXYGEN_CORAL_GENERATE_BUBBLE;
    public static final ForgeConfigSpec.DoubleValue OXYGEN_BUBBLE_SPEED;
    public static final ForgeConfigSpec.BooleanValue DISABLE_WATER_FOG;

    public static List<String> dimensionsWillBeEffectCache = null;



    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("General settings");
        SEA_LEVEL_TOP = builder
                .comment("This value defines top of the sea", "vanilla = 63, default by this mod = 230")
                .defineInRange("sea_level_top", 256, -63, 320);
        SEA_LEVEL_BOTTOM = builder
                .comment("This value defines top of the sea", "vanilla = 63, default by this mod = 230")
                .defineInRange("sea_level_bottom", 60, -63, 320);
        DIMENSIONS_WILL_BE_EFFECT = builder
                .comment("dimensions that this mod will effect(flood by water)")
                .comment("separate by \";\", include name space")
                .define("dimensions_would_be_effect", "minecraft:overworld;");
        builder.pop();

        builder.push("gear settings");
        builder.comment("1 oxygen = 1 bubble").comment("recovery speed means how much oxygen recovery per tick").push("Oxygen tank settings");

        OXYGEN_TANK_CAPACITY_TIER_1 = builder.comment("Oxygen tank capacity tier 1", "default = 1000")
                .defineInRange("oxygen_tank_capacity_tier_1", 1000, 0, Integer.MAX_VALUE);

        OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_1 = builder.comment("Oxygen tank oxygen recovery rate tier 1", "default = 2")
                .defineInRange("oxygen_tank_oxygen_recovery_rate_tier_1", 25, 0, Integer.MAX_VALUE);

        OXYGEN_TANK_CAPACITY_TIER_2 = builder.comment("Oxygen tank capacity tier 2", "default = 2000")
                .defineInRange("oxygen_tank_capacity_tier_2", 2000, 0, Integer.MAX_VALUE);

        OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_2 = builder.comment("Oxygen tank oxygen recovery rate tier 2", "default = 4")
                .defineInRange("oxygen_tank_oxygen_recovery_rate_tier_2", 50, 0, Integer.MAX_VALUE);

        OXYGEN_TANK_CAPACITY_TIER_3 = builder.comment("Oxygen tank capacity tier 3", "default = 4000")
                .defineInRange("oxygen_tank_capacity_tier_3", 4000, 0, Integer.MAX_VALUE);

        OXYGEN_TANK_OXYGEN_RECOVERY_RATE_TIER_3 = builder.comment("Oxygen tank oxygen recovery rate tier 3", "default = 8")
                .defineInRange("oxygen_tank_oxygen_recovery_rate_tier_3", 100, 0, Integer.MAX_VALUE);

        FLIPPER_SPEED_BOOST = builder.comment("Flipper boost speed", "default = 1.5").defineInRange("flipper_boost_speed", 1.5, 0, Double.MAX_VALUE);

        JET_FLIPPER_SPEED_BOOST = builder.comment("Jet flipper speed boost", "default = 1.5").defineInRange("jet_flipper_speed_boost", 1.5, 0, Double.MAX_VALUE);

        JET_FLIPPER_SPEED_ACTIVATED_BOOST = builder.comment("Jet flipper speed boost", "default = 1.5").defineInRange("jet_flipper_speed_boost", 1.5, 0, Double.MAX_VALUE);

        JET_FLIPPER_COST_ON_ACTIVATED_PER_TICK = builder.comment("Jet flipper cost oxygen on activated per tick", "default = 1").defineInRange("jet_flipper_cost_on_activated_per_tick", 1, 0, Integer.MAX_VALUE);

        END_FLIPPER_SPEED_BOOST = builder.comment("End flipper boost speed", "default = 1.5").defineInRange("end_flipper_boost_speed", 1.5, 0, Double.MAX_VALUE);

        END_FLIPPER_TELEPORT_DISTANCE = builder.comment("End flipper teleport distance", "default = 10").defineInRange("end_flipper_teleport_distance", 10, 0, Integer.MAX_VALUE);

        END_FLIPPER_ENERGY_CAPACITY = builder.comment("End flipper energy capacity use to teleport", "gather while move, 2 unit swim speed grants 1 unit energy (floor)", "default = 100").defineInRange("end_flipper_energy_capacity", 100, 0, Integer.MAX_VALUE);

        END_FLIPPER_TELEPORT_COST = builder.comment("End flipper teleport cost energy", "default = 10").defineInRange("end_flipper_teleport_cost", 10, 0, Integer.MAX_VALUE);

        END_FLIPPER_TELEPORT_COOL_DOWN = builder.comment("End flipper teleport cool down", "unit = second", "default = 10").defineInRange("end_flipper_teleport_cool down", 10, 0, Integer.MAX_VALUE);
        builder.pop();
        builder.pop();

        builder.push("technical settings");
        CAN_FURNACE_BURN_UNDER_WATER = builder.comment("can vanilla furnace of any block derived from furnace could burn when facing non-Air block").define("can_furnace_burn_under_water", false);

        SWIFT_DIG_ADDITION = builder.comment("how much amount of \"swift dig\" attribute will addition when use swift paint", "default = 5.0").define("swift_dig_addition", 2d);

        MAX_SWIFT_DIG = builder.comment("max amount of \"swift dig\" attribute", "default = 25, 12.5 = normal speed on ground, 25 means can dig as normal while swimming, but would grant double speed while on ground").define("max_swift_dig", 25d);

        OXYGEN_TANK_HUD_ENABLED = builder.comment("enable oxygen tank hud").define("oxygen_tank_hud_enabled", true);

        DISABLE_WATER_FOG = builder.comment("disable_water_fog", "might cause bug while using shader").define("disable_water_fog", true);
        builder.pop();

        builder.push("world settings");
        ENABLE_FURY_OF_SKY = builder.comment("Enable fury of sky").define("enable_fury_of_sky", true);
        DAMAGE_FURY_OF_SKY = builder.comment("If enabled,damage of fury of sky").defineInRange("damage_fury_of_sky", 4.0f, 0, Double.MAX_VALUE);

        ENABLE_FURY_OF_STRATA = builder.comment("Enable fury of strata").define("enable_fury_of_strata", true);
        DAMAGE_FURY_OF_STRATA = builder.comment("If enabled damage of fury of strata").defineInRange("damage_fury_of_strata", 4.0f, 0, Double.MAX_VALUE);
        OXYGEN_BUBBLE_SPEED = builder.comment("How fast oxygen bubble move").defineInRange("oxygen_bubble_speed", 0.5f, 0, Double.MAX_VALUE);

        builder.pop();

        builder.push("environment settings");
        PROBABILITY_OXYGEN_CORAL_GENERATE_BUBBLE = builder.comment("Probability of generate bubble of oxygen coral per tick", "default = 2").defineInRange("probability_oxygen_coral_generate_bubble", 2, 0, 100);
        builder.pop();


        configSpec = builder.build();
    }

    public static List<String> getDimensionsWillBeEffect() {
        if (dimensionsWillBeEffectCache == null) {
            dimensionsWillBeEffectCache = List.of(DIMENSIONS_WILL_BE_EFFECT.get().split(";"));
        }
        return dimensionsWillBeEffectCache;
    }

    @SubscribeEvent
    public static void onConfigRefresh(ModConfigEvent.Reloading event) {
        dimensionsWillBeEffectCache = List.of(DIMENSIONS_WILL_BE_EFFECT.get().split(";"));
    }

}
