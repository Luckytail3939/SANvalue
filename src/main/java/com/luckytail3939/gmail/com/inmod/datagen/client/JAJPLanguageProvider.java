package com.luckytail3939.gmail.com.inmod.datagen.client;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.effect.ModEffects;
import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

import java.util.Locale;

public class JAJPLanguageProvider extends LanguageProvider {
    public JAJPLanguageProvider(PackOutput output) {
        super(output, SanityMod.MOD_ID, Locale.JAPAN.toString().toLowerCase());
    }

    @Override
    protected void addTranslations() {
        //アイテム
        addItem(SanityItems.RAW_MOONLIGHT_SILVER, "月光銀の原石");
        addItem(SanityItems.RAW_SUNLIGHT_STONE, "太陽石の原石");
        addItem(SanityItems.MOONLIGHT_SILVER_INGOT , "月光銀のインゴット");
        addItem(SanityItems.MOON_SLIME_SPAWN_EGG, "ムーンスライムのスポーンエッグ");

        //  精神薬ポーション
            // 通常ポーション
        add("item.minecraft.potion.effect.mental_medicine_potion", "精神向上のポーション");
        add("item.minecraft.potion.effect.long_mental_medicine_potion", "延長された精神向上のポーション");
        add("item.minecraft.potion.effect.strong_mental_medicine_potion", "強化された精神向上のポーション");

            // スプラッシュ
        add("item.minecraft.splash_potion.effect.mental_medicine_potion", "精神向上のスプラッシュポーション");
        add("item.minecraft.splash_potion.effect.long_mental_medicine_potion", "延長された精神向上のスプラッシュポーション");
        add("item.minecraft.splash_potion.effect.strong_mental_medicine_potion", "強化された精神向上のスプラッシュポーション");

            // 残留
        add("item.minecraft.lingering_potion.effect.mental_medicine_potion", "精神向上の残留ポーション");
        add("item.minecraft.lingering_potion.effect.long_mental_medicine_potion", "延長された精神向上の残留ポーション");
        add("item.minecraft.lingering_potion.effect.strong_mental_medicine_potion", "強化された精神向上の残留ポーション");

            // 矢
        add("item.minecraft.tipped_arrow.effect.mental_medicine_potion", "精神向上の矢");
        add("item.minecraft.tipped_arrow.effect.long_mental_medicine_potion", "延長された精神向上の矢");
        add("item.minecraft.tipped_arrow.effect.strong_mental_medicine_potion", "強化された精神向上の矢");

        //実績
        add("advancement.inmod.sanity.root.title", "SAN値システム");
        add("advancement.inmod.sanity.root.desc", "発狂していってね");
        add("advancement.inmod.sanity.first_loss.title", "初めて正気を失った");
        add("advancement.inmod.sanity.first_loss.desc", "SAN値が減少して正気を失った");
        add("advancement.inmod.sanity.insanity_death.title", "狂気に呑まれたな");
        add("advancement.inmod.sanity.insanity_death.desc", "SAN値が0になって死亡した");

        //エフェクト
        addEffect(ModEffects.MENTAL_MEDICINE, "精神向上");

        //クリエイティブタブ
        add("creativtabs.sanity_tab", "SAN値");

        //ブロック
        addBlock(SanityBlocks.MOONLIGHT_SILVER_BLOCK, "月光銀のブロック");
        addBlock(SanityBlocks.RAW_MOONLIGHT_SILVER_BLOCK, "月光銀の原石ブロック");
        addBlock(SanityBlocks.MOONLIGHT_SILVER_ORE, "月光銀鉱石");
        addBlock(SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE, "深層月光銀鉱石");
        addBlock(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG, "樹皮を剥いだ月光樹の原木");
        addBlock(SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD, "樹皮を剥いだ月光樹");
        addBlock(SanityBlocks.MOONLIT_TREE_LOG, "月光樹の原木");
        addBlock(SanityBlocks.MOONLIT_TREE_WOOD, "月光樹");
        addBlock(SanityBlocks.MOONLIT_TREE_LEAVES, "月光樹の葉");
        addBlock(SanityBlocks.MOONLIT_TREE_PLANKS, "月光樹の板材");
        addBlock(SanityBlocks.MOONLIT_TREE_SLAB, "月光樹のハーフブロック");
        addBlock(SanityBlocks.MOONLIT_TREE_STAIRS, "月光樹の階段");
        addBlock(SanityBlocks.MOONLIT_TREE_FENCE, "月光樹のフェンス");
        addBlock(SanityBlocks.MOONLIT_TREE_FENCE_GATE, "月光樹のフェンスゲート");
        addBlock(SanityBlocks.MOONLIT_TREE_DOOR, "月光樹のドア");
        addBlock(SanityBlocks.MOONLIT_TREE_TRAPDOOR, "月光樹のトラップドア");
        addBlock(SanityBlocks.MOONLIT_TREE_BUTTON, "月光樹のボタン");
        addBlock(SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE, "月光樹の感圧板");
        addBlock(SanityBlocks.MOONLIT_TREE_SAPLING, "月光樹の苗木");
        addBlock(SanityBlocks.MOONLIGHT_FLOWER, "月光花");


        //mob
        addEntityType(SanityEntities.MOON_SLIME,"ムーンスライム");
    }
}
