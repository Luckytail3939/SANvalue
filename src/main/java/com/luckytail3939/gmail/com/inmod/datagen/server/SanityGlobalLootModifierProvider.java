package com.luckytail3939.gmail.com.inmod.datagen.server;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import com.luckytail3939.gmail.com.inmod.loot.AddItemModifier;
import com.luckytail3939.gmail.com.inmod.loot.ReplaceItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class SanityGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public SanityGlobalLootModifierProvider(PackOutput output) {
        super(output, SanityMod.MOD_ID);
    }

    @Override
    protected void start() {

        //廃ポータルのチェスト
        //moonlight_silver_ingot_from_ruined_pportalが生成するjsonファイル名、AddItemModifierがGlobalLootModifier、LootItemConditionで追加する戦利品の条件設定、SanityItems.MOONLIGHT_SILVER_INGOTで追加したいアイテムを設定。
        add("moonlight_silver_ingot_from_ruined_pportal",new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.015f).build()
        }, SanityItems.MOONLIGHT_SILVER_INGOT.get()));

        //ゾンビのドロップ
        //LootItemRandomChanceCondition.randomChanceはドロップ確率
        add("moonlight_silver_ingot_from_zombie",new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build(),
                LootItemRandomChanceCondition.randomChance(0.005f).build()
        }, SanityItems.MOONLIGHT_SILVER_INGOT.get()));

        //スニッファーの掘り出し物
        //ReplaceItemModifierは上書きの方
        add("moonlight_silver_ingot_from_sus_sand",new ReplaceItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/sniffer_digging")).build()
        }, SanityItems.MOONLIGHT_SILVER_INGOT.get(),0.125f));

        //怪しげな砂
        add("moonlight_silver_ingot_from_sniffer_digging",new ReplaceItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()
        }, SanityItems.MOONLIGHT_SILVER_INGOT.get(),0.2f));

    }
}
