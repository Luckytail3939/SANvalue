package com.luckytail3939.gmail.com.inmod.potion.mental_medicine_potion;

import com.luckytail3939.gmail.com.inmod.potion.ModPotions;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class StrongMentalMedicineRecipe implements IBrewingRecipe {
    @Override
    public boolean isInput(ItemStack input) {
        return input.getItem() == Items.POTION
                && PotionUtils.getPotion(input) == ModPotions.MENTAL_MEDICINE_POTION.get();
    }

    @Override
    public boolean isIngredient(ItemStack input) {
        return input.getItem() == Items.GLOWSTONE_DUST;
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (isInput(input) && isIngredient(ingredient)) {
            return PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.STRONG_MENTAL_MEDICINE_POTION.get());
        }
        return ItemStack.EMPTY;
    }
}
