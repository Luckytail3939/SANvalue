package com.luckytail3939.gmail.com.inmod.datagen.server;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.wordgen.biome.SanityBiomeModifiers;
import com.luckytail3939.gmail.com.inmod.wordgen.biome.SanityBiomes;
import com.luckytail3939.gmail.com.inmod.wordgen.features.SanityFeatures;
import com.luckytail3939.gmail.com.inmod.wordgen.placement.SanityPlacement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class SanityWorldGenProvider extends DatapackBuiltinEntriesProvider {

    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, SanityFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, SanityPlacement::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, SanityBiomeModifiers::bootstrap)
            .add(Registries.BIOME, SanityBiomes::bootstrap);
    public SanityWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,BUILDER,Set.of(SanityMod.MOD_ID));
    }
}
