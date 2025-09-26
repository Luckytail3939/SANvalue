package com.luckytail3939.gmail.com.inmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SanityCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // ▼ SAN値減少 --------------------------
    public static final ForgeConfigSpec.DoubleValue DARKNESS_DECREASE;
    public static final ForgeConfigSpec.DoubleValue CAVE_DECREASE;
    public static final ForgeConfigSpec.DoubleValue MOB_NEARBY_DECREASE;
    public static final ForgeConfigSpec.DoubleValue SLEEPLESS_DECREASE;
    public static final ForgeConfigSpec.DoubleValue LOW_HEALTH_DECREASE;

    // ▼ SAN値回復 --------------------------
    public static final ForgeConfigSpec.DoubleValue SLEEP_RECOVERY;
    public static final ForgeConfigSpec.DoubleValue CAMPFIRE_RECOVERY;
    public static final ForgeConfigSpec.DoubleValue FOOD_RECOVERY;
    public static final ForgeConfigSpec.DoubleValue XP_RECOVERY;
    public static final ForgeConfigSpec.DoubleValue POTION_RECOVERY;

    // 洞窟判定に使うY座標の閾値
    public static final ForgeConfigSpec.IntValue CAVE_Y_LEVEL;


    static {
        BUILDER.push("Sanity Settings");

        // 減少設定
        DARKNESS_DECREASE = BUILDER.comment("SAN decrease per second when in darkness (light <= 3)")
                .defineInRange("darknessDecrease", 0.1D, 0.0D, 100.0D);

        CAVE_DECREASE = BUILDER.comment("SAN decrease per second when underground (no sky)")
                .defineInRange("caveDecrease", 0.3D, 0.0D, 100.0D);

        MOB_NEARBY_DECREASE = BUILDER.comment("SAN decrease per second when hostile mobs are nearby")
                .defineInRange("mobNearbyDecrease", 0.5D, 0.0D, 100.0D);

        SLEEPLESS_DECREASE = BUILDER.comment("SAN decrease per second when not slept for 3+ days")
                .defineInRange("sleeplessDecrease", 0.5D, 0.0D, 100.0D);

        LOW_HEALTH_DECREASE = BUILDER.comment("SAN decrease per second when health <= 25%")
                .defineInRange("lowHealthDecrease", 1.0D, 0.0D, 100.0D);

        //洞窟判定高さ
        CAVE_Y_LEVEL = BUILDER.comment("Maximum Y level to count as cave (default 60)")
                .defineInRange("caveYLevel", 60, -64, 320);

        // 回復設定
        SLEEP_RECOVERY = BUILDER.comment("SAN recovery when sleeping in bed")
                .defineInRange("sleepRecovery", 10.0D, 0.0D, 100.0D);

        CAMPFIRE_RECOVERY = BUILDER.comment("SAN recovery when resting near campfire")
                .defineInRange("campfireRecovery", 3.0D, 0.0D, 100.0D);

        FOOD_RECOVERY = BUILDER.comment("SAN recovery when eating food (penalty if repeated)")
                .defineInRange("foodRecovery", 2.0D, 0.0D, 100.0D);

        XP_RECOVERY = BUILDER.comment("SAN recovery when picking up XP")
                .defineInRange("xpRecovery", 0.5D, 0.0D, 100.0D);

        POTION_RECOVERY = BUILDER.comment("SAN recovery when drinking mental medicine potion")
                .defineInRange("potionRecovery", 20.0D, 0.0D, 100.0D);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
