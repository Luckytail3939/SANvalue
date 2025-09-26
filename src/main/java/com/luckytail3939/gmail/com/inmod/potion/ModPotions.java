package com.luckytail3939.gmail.com.inmod.potion;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.effect.ModEffects;
import com.luckytail3939.gmail.com.inmod.potion.mental_medicine_potion.LongMentalMedicineRecipe;
import com.luckytail3939.gmail.com.inmod.potion.mental_medicine_potion.MentalMedicineBrewingRecipe;
import com.luckytail3939.gmail.com.inmod.potion.mental_medicine_potion.StrongMentalMedicineRecipe;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

//ポーションを登録するクラス
@Mod.EventBusSubscriber(modid = SanityMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(net.minecraft.core.registries.Registries.POTION, SanityMod.MOD_ID);

    public static final RegistryObject<Potion> MENTAL_MEDICINE_POTION =
            POTIONS.register("mental_medicine_potion",
                    () -> new Potion(new net.minecraft.world.effect.MobEffectInstance(ModEffects.MENTAL_MEDICINE.get(), 600)));//30秒

    public static final RegistryObject<Potion> LONG_MENTAL_MEDICINE_POTION =
            POTIONS.register("long_mental_medicine_potion",
                    () -> new Potion(new MobEffectInstance(ModEffects.MENTAL_MEDICINE.get(), 1200))); // 1分

    public static final RegistryObject<Potion> STRONG_MENTAL_MEDICINE_POTION =
            POTIONS.register("strong_mental_medicine_potion",
                    () -> new Potion(new MobEffectInstance(ModEffects.MENTAL_MEDICINE.get(), 200, 1))); // 10秒, レベル2

    // FMLCommonSetupEvent で醸造レシピを登録
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // --- 基本: Awkward + Moonlight Flower = Mental Medicine ---
            BrewingRecipeRegistry.addRecipe(new MentalMedicineBrewingRecipe(
                    Ingredient.of(SanityBlocks.MOONLIGHT_FLOWER.get()),
                    MENTAL_MEDICINE_POTION.get()
            ));

            // --- 延長 ---
            BrewingRecipeRegistry.addRecipe(new LongMentalMedicineRecipe());

            // --- 強化 ---
            BrewingRecipeRegistry.addRecipe(new StrongMentalMedicineRecipe());

            // --- スプラッシュ化 (全種類) ---
            addSplashAndLingeringRecipes(ModPotions.MENTAL_MEDICINE_POTION.get());
            addSplashAndLingeringRecipes(ModPotions.LONG_MENTAL_MEDICINE_POTION.get());
            addSplashAndLingeringRecipes(ModPotions.STRONG_MENTAL_MEDICINE_POTION.get());
        });
    }

    // スプラッシュ & 残留レシピを一括登録
    private static void addSplashAndLingeringRecipes(Potion potion) {
        // スプラッシュ (火薬)
        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), potion)),
                Ingredient.of(Items.GUNPOWDER),
                PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion)
        );

        // 残留 (ドラゴンブレス)
        BrewingRecipeRegistry.addRecipe(
                Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), potion)),
                Ingredient.of(Items.DRAGON_BREATH),
                PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), potion)
        );
    }

}
