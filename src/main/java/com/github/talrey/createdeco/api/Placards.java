package com.github.talrey.createdeco.api;

import com.zurrtum.create.AllItems;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

public class Placards {

  public static <T extends Block> void recipeCrafting (DyeColor color, DataGenContext<Block, T> ctx, RegistrateRecipeProvider prov) {
    ShapelessRecipeBuilder.shapeless(null, RecipeCategory.DECORATIONS, ctx.get())
      .requires(Items.ITEM_FRAME)
      .requires(AllItems.BRASS_SHEET)
      .requires(DyeItems.byColor(color))
      .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
          ItemPredicate.Builder.item().of(null, AllItems.BRASS_SHEET).build()
      ))
      .save(prov, color.getName() + "_placard");
  }


  public static <T extends Block> void recipeDyeing (DyeColor color, DataGenContext<Block, T> ctx, RegistrateRecipeProvider prov) {
    ShapelessRecipeBuilder.shapeless(null, RecipeCategory.DECORATIONS, ctx.get())
      .requires(CreateDecoTags.PLACARDS)
      .requires(DyeItems.byColor(color))
      .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
          ItemPredicate.Builder.item().of(null, CreateDecoTags.PLACARDS).build()
      ))
      .unlockedBy("has_dye", InventoryChangeTrigger.TriggerInstance.hasItems(
          ItemPredicate.Builder.item().of(null, DyeItems.byColor(color)).build()
      ))
      .group("dye_placard")
      .save(prov, color.getName() + "_placard_from_dyeing");
  }
}
