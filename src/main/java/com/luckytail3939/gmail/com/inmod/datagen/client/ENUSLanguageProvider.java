package com.luckytail3939.gmail.com.inmod.datagen.client;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.effect.ModEffects;
import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

public class ENUSLanguageProvider extends LanguageProvider {
    public ENUSLanguageProvider(PackOutput output) {
        super(output, SanityMod.MOD_ID, Locale.US.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        //アイテム
        addItem(SanityItems.RAW_MOONLIGHT_SILVER, "Raw MoonlightSilver");
        addItem(SanityItems.RAW_SUNLIGHT_STONE, "Raw SunlightStone");
        addItem(SanityItems.MOONLIGHT_SILVER_INGOT , "MoonlightSilver Ingot");
        addItem(SanityItems.MOON_SLIME_SPAWN_EGG, "MoonSlime SpawnEgg");

        //  精神薬ポーション
            // 通常ポーション
        add("item.minecraft.potion.effect.mental_medicine_potion", "Potion of Mental Medicine");
        add("item.minecraft.potion.effect.long_mental_medicine_potion", "Potion of Mental Medicine (Long)");
        add("item.minecraft.potion.effect.strong_mental_medicine_potion", "Potion of Mental Medicine (Strong)");

            // スプラッシュ
        add("item.minecraft.splash_potion.effect.mental_medicine_potion", "Splash Potion of Mental Medicine");
        add("item.minecraft.splash_potion.effect.long_mental_medicine_potion", "Splash Potion of Mental Medicine (Long)");
        add("item.minecraft.splash_potion.effect.strong_mental_medicine_potion", "Splash Potion of Mental Medicine (Strong)");

            // 残留
        add("item.minecraft.lingering_potion.effect.mental_medicine_potion", "Lingering Potion of Mental Medicine");
        add("item.minecraft.lingering_potion.effect.long_mental_medicine_potion", "Lingering Potion of Mental Medicine (Long)");
        add("item.minecraft.lingering_potion.effect.strong_mental_medicine_potion", "Lingering Potion of Mental Medicine (Strong)");

            // 矢
        add("item.minecraft.tipped_arrow.effect.mental_medicine_potion", "Arrow of Mental Medicine");
        add("item.minecraft.tipped_arrow.effect.long_mental_medicine_potion", "Arrow of Mental Medicine (Long)");
        add("item.minecraft.tipped_arrow.effect.strong_mental_medicine_potion", "Arrow of Mental Medicine (Strong)");

        //実績
        add("advancement.inmod.sanity.root.title", "SAN value system");
        add("advancement.inmod.sanity.root.desc", "Go crazy.");
        add("advancement.inmod.sanity.first_loss.title", "I lost my mind for the first time");
        add("advancement.inmod.sanity.first_loss.desc", "Lost sanity due to decreased SAN value");
        add("advancement.inmod.sanity.insanity_death.title", "I was consumed by madness");
        add("advancement.inmod.sanity.insanity_death.desc", "Died when SAN reached 0");

        //エフェクト
        addEffect(ModEffects.MENTAL_MEDICINE, "Mental Medicine");

        //クリエイティブタブ
        add("creativtabs.sanity_tab", "Sanity");

        //ブロック
        addBlock(SanityBlocks.MOONLIGHT_SILVER_BLOCK, "MoonlightSilver Block");
        addBlock(SanityBlocks.RAW_MOONLIGHT_SILVER_BLOCK, "Raw MoonlightSilver Block");
        addBlock(SanityBlocks.MOONLIGHT_SILVER_ORE, "MoonlightSilver Ore");
        addBlock(SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE, "Deepslate MoonlightSilver Ore");
        addBlock(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG, "Stripped MoonlitTree Log");
        addBlock(SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD, "Stripped MoonlitTree Wood");
        addBlock(SanityBlocks.MOONLIT_TREE_LOG, "MoonlitTree Log");
        addBlock(SanityBlocks.MOONLIT_TREE_WOOD, "MoonlitTree Wood");
        addBlock(SanityBlocks.MOONLIT_TREE_LEAVES, "MoonlitTree Leaves");
        addBlock(SanityBlocks.MOONLIT_TREE_PLANKS, "MoonlitTree Planks");
        addBlock(SanityBlocks.MOONLIT_TREE_SLAB, "MoonlitTree Slab");
        addBlock(SanityBlocks.MOONLIT_TREE_STAIRS, "MoonlitTree Stairs");
        addBlock(SanityBlocks.MOONLIT_TREE_FENCE, "MoonlitTree Fence");
        addBlock(SanityBlocks.MOONLIT_TREE_FENCE_GATE, "MoonlitTree FenceGate");
        addBlock(SanityBlocks.MOONLIT_TREE_DOOR, "MoonlitTree Door");
        addBlock(SanityBlocks.MOONLIT_TREE_TRAPDOOR, "MoonlitTree TrapDoor");
        addBlock(SanityBlocks.MOONLIT_TREE_BUTTON, "MoonlitTree Button");
        addBlock(SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE, "MoonlitTree PressurePlate");
        addBlock(SanityBlocks.MOONLIT_TREE_SAPLING, "MoonlitTree Sapling");
        addBlock(SanityBlocks.MOONLIGHT_FLOWER, "Moonlight Flower");


        //mob
        addEntityType(SanityEntities.MOON_SLIME,"moon Slime");
    }
}
