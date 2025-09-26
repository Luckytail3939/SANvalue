package com.luckytail3939.gmail.com.inmod.wordgen.placement;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.wordgen.features.SanityFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class SanityPlacement {
    //リソースキー
    public static final ResourceKey<PlacedFeature> ORE_MOONLIGHT_SILVER =
            createKey("ore_moonlight_silver");

    public static final ResourceKey<PlacedFeature> MOONLIGHT_TREE =
            createKey("moonlight_tree");

    public static final ResourceKey<PlacedFeature> MOONLIGHT_FLOWER =
            createKey("moonlight_flower");

    //  配置設定メソッド
    public static void bootstrap(BootstapContext<PlacedFeature> context){
        HolderGetter<ConfiguredFeature<?,?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);/*OreFeaturesの設定を読み込むために必要*/

        // 鉱石の配置情報を設定
        PlacementUtils.register(context,ORE_MOONLIGHT_SILVER,
                configuredFeatures.getOrThrow(SanityFeatures.MOONLIGHT_SILVER_ORE_KEY),
                commonOrePlacement(2,/*鉱石が生成される量*/
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-64),/*HeightRangePlacementのuniformメソッドで指定高さの中で均一に鉱石を生成。triangleメソッドを代わりに使うと指定した高さの真ん中で一番多く生成されるようになる。2025_08_23uniformからtriangleに変更*/
                                VerticalAnchor.absolute(10))));/*今回は-64~-10の間で鉱石が生成されるようになっている*/

        // 木の配置情報を設定
        PlacementUtils.register(context, MOONLIGHT_TREE,
                configuredFeatures.getOrThrow(SanityFeatures.MOONLIT_TREE_KEY),
                VegetationPlacements.treePlacement(
                        PlacementUtils.countExtra(10, 0.1f, 1),
                        SanityBlocks.MOONLIT_TREE_SAPLING.get()));

        // 花
        PlacementUtils.register(context, MOONLIGHT_FLOWER,
                configuredFeatures.getOrThrow(SanityFeatures.MOONLIGHT_FLOWER_KEY),
                RarityFilter.onAverageOnceEvery(16),// ← レア度
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());
    }

    //リソースキー登録用メソッド
    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                new ResourceLocation(SanityMod.MOD_ID, name));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier pCountPlacement, PlacementModifier pHeightRange) {
        return List.of(pCountPlacement, InSquarePlacement.spread(), pHeightRange, BiomeFilter.biome());
    }

    //一般的な鉱石の設置メソッド
    private static List<PlacementModifier> commonOrePlacement(int pCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(pCount), pHeightRange);
    }

    //ダイヤなどレア鉱石の設置メソッド
    private static List<PlacementModifier> rareOrePlacement(int pChance, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange);
    }
}
