package com.luckytail3939.gmail.com.inmod.wordgen.features;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class SanityFeatures {

    //月光花リソースキー作成
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOONLIGHT_FLOWER_KEY =
            createKey("moonlight_flower");

    //月光銀鉱石置き変えリソースキー作成
    public static final ResourceKey<ConfiguredFeature<?,?>> MOONLIGHT_SILVER_ORE_KEY=
            createKey("moonlight_silver_ore");

    //月光樹リソースキー作成
    public static final ResourceKey<ConfiguredFeature<?,?>> MOONLIT_TREE_KEY=
            createKey("moonlit_tree");

    //置き換えルールの登録
    public static void bootstrap(BootstapContext<ConfiguredFeature<?,?>>context){
        RuleTest stone = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);/*STONE_ORE_REPLACEABLESは石や花崗岩など、鉱石と置き換える予定のブロックをまとめたタグ*/
        RuleTest deepslate = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);/*深層岩用*/

        //上で設定した石系のブロックを月光銀鉱石と置き換えるルールを作成
        List<OreConfiguration.TargetBlockState> moonlightSilverOres = List.of(
                OreConfiguration.target(stone,
                        SanityBlocks.MOONLIGHT_SILVER_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslate,
                        SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE.get().defaultBlockState())
        );

        FeatureUtils.register(context,MOONLIGHT_SILVER_ORE_KEY, Feature.ORE,new OreConfiguration(moonlightSilverOres,4));/*数字は鉱石がまとまって生成される数。だが文字通りには生成されない。OreFeaturesクラスのバニラの設定を参考にしてくれ。*/

        FeatureUtils.register(context,MOONLIT_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(SanityBlocks.MOONLIT_TREE_LOG.get()),/*木の幹の設定。一行目が幹のブロックの種類。二行目が幹の形。*/
                        new StraightTrunkPlacer(8,3,7),/*TrunkPlacerクラスをいじることで、幹の形を変更できる。StraightTrunkPlacerは白樺のようにまっすぐ。*/
                        BlockStateProvider.simple(SanityBlocks.MOONLIT_TREE_LEAVES.get()),/*三、四行目では葉の設定。三行目で葉のブロックの種類。四行目で葉の形を設定。*/
                        new BlobFoliagePlacer(ConstantInt.of(2),/*FoliagePlacerクラスをいじる事で、葉の形を変更できる。*/
                                ConstantInt.of(2),6),
                        new TwoLayersFeatureSize(1,0,2)).build());/*五行目では、木の生成に必要なスペースの設定。*/

        // 花の登録
        FeatureUtils.register(context, MOONLIGHT_FLOWER_KEY, Feature.FLOWER,
                new RandomPatchConfiguration(32, 6, 2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(BlockStateProvider.simple(SanityBlocks.MOONLIGHT_FLOWER.get()))
                        )
                ));
    }

    //リソースキー登録用メソッド
    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,new ResourceLocation(SanityMod.MOD_ID,name));
    }
}
//2025_08_23今日はOreFeaturesクラスの実装まで、もう夜中の３時なので眠いです。でも今日買ったロードエルメロイ二世の小説が読みたいからもうちょっと起きてるかも。Kindleって便利やな。