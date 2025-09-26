package com.luckytail3939.gmail.com.inmod.wordgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

public class SanityOverworldRegion extends Region {
    public SanityOverworldRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {/*TerraBlenderのサンプルコードを参考*/
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();
        new ParameterUtils.ParameterPointListBuilder()
                .continentalness(ParameterUtils.Continentalness.INLAND)/*生成する土地の種類を選択*/
                .depth(ParameterUtils.Depth.FULL_RANGE)/*バイオームを適用する深さを設定*/
                .build().forEach(point -> builder.add(point, SanityBiomes.MOONLIGHT_FOREST));

        builder.build().forEach(mapper);
    }
}
