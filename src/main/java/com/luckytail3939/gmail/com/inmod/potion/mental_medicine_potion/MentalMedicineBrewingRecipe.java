package com.luckytail3939.gmail.com.inmod.potion.mental_medicine_potion;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class MentalMedicineBrewingRecipe implements IBrewingRecipe {
    private final Ingredient reagent;
    private final Potion outputPotion;

    public MentalMedicineBrewingRecipe(Ingredient reagent, Potion outputPotion) {
        this.reagent = reagent;
        this.outputPotion = outputPotion;
    }

    @Override
    public boolean isInput(ItemStack input) {
        return input.getItem() == Items.POTION && PotionUtils.getPotion(input) == Potions.AWKWARD;
    }

    @Override
    public boolean isIngredient(ItemStack input) {
        return reagent.test(input);
    }

    @Override
    public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
        if (isInput(input) && isIngredient(ingredient)) {
            return PotionUtils.setPotion(new ItemStack(Items.POTION), outputPotion);
        }
        return ItemStack.EMPTY;
    }
}
