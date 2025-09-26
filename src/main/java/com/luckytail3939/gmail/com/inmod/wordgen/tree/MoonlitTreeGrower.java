package com.luckytail3939.gmail.com.inmod.wordgen.tree;

import com.luckytail3939.gmail.com.inmod.wordgen.features.SanityFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class MoonlitTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return SanityFeatures.MOONLIT_TREE_KEY;
    }
}
