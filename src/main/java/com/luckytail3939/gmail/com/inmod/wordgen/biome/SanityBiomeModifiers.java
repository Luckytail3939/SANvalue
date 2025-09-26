package com.luckytail3939.gmail.com.inmod.wordgen.biome;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.wordgen.placement.SanityPlacement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class SanityBiomeModifiers {
    //リソースキー作成
    public static final ResourceKey<BiomeModifier> ADD_MOONLIGHT_SILVER_ORE =
            creatKey("add_moonlight_silver_ore");

    //バイオームに鉱石生成を追加するメソッド
    public static void bootstrap(BootstapContext<BiomeModifier> context){
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);/*OrePlacementクラスを読み込むため*/
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);/*鉱石を生成するバイオームを取得するために必要*/
        context.register(ADD_MOONLIGHT_SILVER_ORE,new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),/*バイオーム変更*/
                HolderSet.direct(placedFeatures.getOrThrow(SanityPlacement.ORE_MOONLIGHT_SILVER)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
    }

    //リソースキー登録用メソッド
    private static ResourceKey<BiomeModifier> creatKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,new ResourceLocation(SanityMod.MOD_ID, name));
    }
}
