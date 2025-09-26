package com.luckytail3939.gmail.com.inmod.datagen;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.datagen.client.ENUSLanguageProvider;
import com.luckytail3939.gmail.com.inmod.datagen.client.JAJPLanguageProvider;
import com.luckytail3939.gmail.com.inmod.datagen.client.SanityBlockStateProvider;
import com.luckytail3939.gmail.com.inmod.datagen.client.SanityItemModelProvider;
import com.luckytail3939.gmail.com.inmod.datagen.server.*;
import com.luckytail3939.gmail.com.inmod.datagen.server.loot.SanityLootTables;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

//jsonファイルの生成

@Mod.EventBusSubscriber(modid = SanityMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SanityDataGenerators {

    @SubscribeEvent
    public static  void  gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookUpProvider = event.getLookupProvider();

        //アイテムジェネレータ
        generator.addProvider(event.includeClient(), new SanityItemModelProvider(packOutput, existingFileHelper));

        //ブロックジェネレータ
        generator.addProvider(event.includeClient(), new SanityBlockStateProvider(packOutput, existingFileHelper));

        //言語ジェネレータ（英語）
        generator.addProvider(event.includeClient(), new ENUSLanguageProvider(packOutput));
        //言語ジェネレータ（日本語）
        generator.addProvider(event.includeClient(), new JAJPLanguageProvider(packOutput));

        //レシピジェネレータ
        generator.addProvider(event.includeServer(), new SanityRecpieProvider(packOutput));

        //ルートテーブルジェネレータ
        generator.addProvider(event.includeServer(), SanityLootTables.create(packOutput));

        //ブロックタグジェネレータ
        var blockTagsProvider = generator.addProvider(event.includeServer(),new SanityBlockTagsProvider(packOutput,lookUpProvider,existingFileHelper));

        //アイテムタグジェネレータ
        generator.addProvider(event.includeServer(), new SanityItemTagsProvider(packOutput,lookUpProvider,blockTagsProvider.contentsGetter(),existingFileHelper));

        //GlobalLootModifierジェネレータ
        generator.addProvider(event.includeServer(),new SanityGlobalLootModifierProvider(packOutput));

        //WorldGenジェネレータ
        generator.addProvider(event.includeServer(),new SanityWorldGenProvider(packOutput,lookUpProvider));

        //Advancementジェネレータ
        generator.addProvider(event.includeServer(), new ForgeAdvancementProvider(packOutput, lookUpProvider, existingFileHelper, java.util.List.of(new SanityAdvancementGenerator())));
    }
}
