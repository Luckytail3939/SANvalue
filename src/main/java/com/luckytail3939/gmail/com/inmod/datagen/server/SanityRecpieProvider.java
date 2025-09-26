package com.luckytail3939.gmail.com.inmod.datagen.server;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import com.luckytail3939.gmail.com.inmod.tag.SanityTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.function.Consumer;

public class SanityRecpieProvider extends RecipeProvider{

    //月光銀インゴットを生成出来るアイテムのリスト
    private static final List<ItemLike> MOONLIGHT_SILVER_SMELTABLE =
            List.of(SanityItems.RAW_MOONLIGHT_SILVER.get(),
                    SanityBlocks.MOONLIGHT_SILVER_ORE.get(),
                    SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE.get());

    public SanityRecpieProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        //インゴットからブロックを作るレシピ
        nineBlockStorageRecipes(consumer, RecipeCategory.MISC, SanityItems.MOONLIGHT_SILVER_INGOT.get(),
                RecipeCategory.BUILDING_BLOCKS, SanityBlocks.MOONLIGHT_SILVER_BLOCK.get());
        //原石からインゴットを精錬するレシピ。ver-1.20.1からRecipeOutputクラスが使用出来ないので、Consumer<FinishedRecipe>をしようしてレシピを登録する事。精錬可能なアイテムリスト、カテゴリ、結果、経験値、精錬時間、レシピグループ名の順番で記述してね。
        //かまどの精錬速度は通常200だけど、あえて300にしてます。
        oreSmelting(consumer,MOONLIGHT_SILVER_SMELTABLE,RecipeCategory.MISC,SanityItems.MOONLIGHT_SILVER_INGOT.get(),1.0f,300,"moonlight_silver");
        oreBlasting(consumer,MOONLIGHT_SILVER_SMELTABLE,RecipeCategory.MISC,SanityItems.MOONLIGHT_SILVER_INGOT.get(),1.0f,200,"moonlight_silver");

        //木材関連
        woodFromLogs(consumer,SanityBlocks.MOONLIT_TREE_WOOD.get(),SanityBlocks.MOONLIT_TREE_LOG.get());
        woodFromLogs(consumer,SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD.get(),SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get());

        //板材レシピ
        planksFromLog(consumer,SanityBlocks.MOONLIT_TREE_PLANKS.get(), SanityTags.Items.MOONLIT_TREE_LOG,4);//MOONLIT_TREE_LOGに登録したアイテムが全て板材の材料になる。

        //ハーフブロックレシピ
        slab(consumer,RecipeCategory.BUILDING_BLOCKS,SanityBlocks.MOONLIT_TREE_SLAB.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

        //階段レシピ
        stairs(consumer,SanityBlocks.MOONLIT_TREE_STAIRS.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

        //フェンス
        fence(consumer,SanityBlocks.MOONLIT_TREE_FENCE.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

        //フェンスゲート
        fenceGate(consumer,SanityBlocks.MOONLIT_TREE_FENCE_GATE.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

        //ドア
        door(consumer,SanityBlocks.MOONLIT_TREE_DOOR.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

        //トラップドア
        trapdoor(consumer,SanityBlocks.MOONLIT_TREE_TRAPDOOR.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

        //ボタン
        button(consumer,SanityBlocks.MOONLIT_TREE_BUTTON.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

        //感圧版
        pressurePlate(consumer,SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE.get(),SanityBlocks.MOONLIT_TREE_PLANKS.get());

    }
    //かまど用のレシピ
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }
    //溶鉱炉用のメソッド
    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    //上二つの共通処理
    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,
                            SanityMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer,
                                                  RecipeCategory pUnpackedCategory,
                                                  ItemLike pUnpacked,
                                                  RecipeCategory pPackedCategory,
                                                  ItemLike pPacked) {
        ShapelessRecipeBuilder.shapeless(pUnpackedCategory, pUnpacked, 9)
                .requires(pPacked).unlockedBy(getHasName(pPacked), has(pPacked)).save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(pPackedCategory, pPacked).define('#', pUnpacked)
                .pattern("###").pattern("###").pattern("###")
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked)).save(pFinishedRecipeConsumer);
    }

    private static void stairs(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pIngredient) {
        stairBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer);
    }
    private static void fence(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pIngredient) {
        fenceBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer);
    }
    private static void fenceGate(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult,
                                  ItemLike pIngredient) {
        fenceGateBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer);
    }
    private static void door(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pIngredient) {
        doorBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer);
    }
    private static void trapdoor(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult,
                                 ItemLike pIngredient) {
        trapdoorBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer);
    }
    private static void button(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pResult, ItemLike pIngredient) {
        buttonBuilder(pResult, Ingredient.of(pIngredient))
                .unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer);
    }
}
