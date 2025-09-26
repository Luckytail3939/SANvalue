package com.luckytail3939.gmail.com.inmod.wordgen.biome;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import com.luckytail3939.gmail.com.inmod.wordgen.placement.SanityPlacement;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class SanityBiomes {
    //リソースキー作成
    public static final ResourceKey<Biome> MOONLIGHT_FOREST =
            ResourceKey.create(Registries.BIOME,new ResourceLocation(
                    SanityMod.MOD_ID,"moonlight_forest"));

    //バイオームの設定登録
    public static void bootstrap(BootstapContext<Biome> context){
        context.register(MOONLIGHT_FOREST,moonlightForest(context));
    }

    // OverworldBiomesクラスからコピー
    private static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    private static Biome moonlightForest(BootstapContext<Biome> context) {
        // モブのスポーンの設定
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(
                        SanityEntities.MOON_SLIME.get(), 100, 4, 4));

        // ヒツジなど
//        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        // ゾンビ、スケルトン、クリーパーなど
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        // Carver：渓谷や洞窟を設置するもの
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE),
                        context.lookup(Registries.CONFIGURED_CARVER));

        // バニラの設定をいい感じにコピってくる（順番をバニラ通りにしないとエラーが起きる）
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        // かぼちゃ、サトウキビ
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        // 月光樹
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                SanityPlacement. MOONLIGHT_TREE);
        //月光花
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                SanityPlacement.MOONLIGHT_FLOWER);


        return new Biome.BiomeBuilder()
                // 雨が降るかどうか
                .hasPrecipitation(true)
                // 降水確率（沼地：0.9f 森林：0.8f）
                .downfall(0.8f)
                // 気温（森林：0.7f メサ：2.0f 雪タイガ：-0.5f）
                .temperature(0.6f)
                // 上の設定を読み込ませる
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                // その他の設定
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(4422295)
                        .waterFogColor(4422272)
                        .skyColor(8174529)
                        .grassColorOverride(1327686)
                        .foliageColorOverride(12512754)
                        .fogColor(13235199)
                        .ambientParticle(new AmbientParticleSettings(ParticleTypes.ASH, 0.00625F))
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
                        .build())
                .build();
    }
}
