package com.luckytail3939.gmail.com.inmod.wordgen.biome;

import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class SanitySurfaceRuleData {
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    /*private static final SurfaceRules.RuleSource MOONLIGHT_SILVER =
            makeStateRule(SanityBlocks.MOONLIGHT_SILVER_BLOCK.get());*/

    public static SurfaceRules.RuleSource makeRules() {
        return SurfaceRules.sequence(
                //月光樹林バイオームの地面を泥ブロックにする
                SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(SanityBiomes.MOONLIGHT_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, MUD))
                // 月光樹林バイオームの天井を月光銀ブロックにする
                /*,SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(SanityBiomes.MOONLIGHT_FOREST),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, MOONLIGHT_SILVER))*/
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
