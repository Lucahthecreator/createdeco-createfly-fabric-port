package com.tterrag.registrate.util;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class DataIngredient {
    public static Ingredient items(ItemLike item) {
        return Ingredient.of(item);
    }
}
