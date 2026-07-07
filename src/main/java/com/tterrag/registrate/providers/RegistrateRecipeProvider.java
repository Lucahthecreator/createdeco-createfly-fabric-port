package com.tterrag.registrate.providers;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import com.tterrag.registrate.util.entry.ItemEntry;
import java.util.function.Supplier;

public class RegistrateRecipeProvider implements RecipeOutput {
    @SuppressWarnings("unchecked")
    public static <T> T has(ItemLike item) {
        return (T) InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    public <T extends Block> void stairs(Ingredient ingredient, RecipeCategory category, DataGenContext<Block, T> ctx, String namespace, boolean unlock) {
    }

    public <T extends Block> void slab(Ingredient ingredient, RecipeCategory category, DataGenContext<Block, T> ctx, String namespace, boolean unlock) {
    }

    public <T extends Block> void wall(Ingredient ingredient, RecipeCategory category, DataGenContext<Block, T> ctx) {
    }

    public <T extends Block> void stonecutting(Ingredient ingredient, RecipeCategory category, DataGenContext<Block, T> ctx, int count) {
    }

    public <T extends Block> void smeltingAndBlasting(Ingredient ingredient, RecipeCategory category, DataGenContext<Block, T> ctx, float xp) {
    }

    public void storage(DataGenContext<?, ?> ctx, RecipeCategory category, Supplier<? extends ItemLike> source) {
    }

    public void storage(DataGenContext<?, ?> ctx, RecipeCategory category, ItemEntry<?> source) {
    }

    public void storage(DataGenContext<?, ?> ctx, RecipeCategory category, ItemLike source) {
    }

    @Override
    public void accept(ResourceKey<Recipe<?>> key, Recipe<?> recipe, AdvancementHolder advancement) {
    }

    @Override
    public Advancement.Builder advancement() {
        return Advancement.Builder.advancement();
    }

    @Override
    public void includeRootAdvancement() {
    }
}
