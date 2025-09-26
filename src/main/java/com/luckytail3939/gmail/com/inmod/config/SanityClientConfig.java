package com.luckytail3939.gmail.com.inmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class SanityClientConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue hudX;
    public static final ForgeConfigSpec.IntValue hudY;

    static {
        BUILDER.push("HUD Settings");

        hudX = BUILDER.comment("HUD X position")
                .defineInRange("hudX", 30, 0, 5000);

        hudY = BUILDER.comment("HUD Y position")
                .defineInRange("hudY", 10, 0, 5000);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
