package com.github.talrey.createdeco.api;

import com.github.talrey.createdeco.BlockStateGenerator;
import com.github.talrey.createdeco.blocks.DecoBarsBlock;
import com.zurrtum.create.AllTags;
import com.zurrtum.create.foundation.data.CreateDecoRegistrate;
import com.github.talrey.createdeco.registrate.BlockBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.Locale;
import java.util.function.Supplier;

public class Bars {
  public static BlockBuilder<DecoBarsBlock, ?> build (
          CreateDecoRegistrate reg, String metal, String suffix, boolean doPost
  ) {
    String base = metal.replace(' ', '_').toLowerCase(Locale.ROOT).replaceAll(" ", "_") + "_bars";
    String suf = suffix.equals("") ? "" : "_" + suffix.replace(' ', '_').toLowerCase(Locale.ROOT);
    String post = "block/palettes/metal_bars/" + base + (doPost ? "_post" : "");

    Identifier barTexture, postTexture;
    final Identifier bartex, postex;
    //try {
    if (metal.equals("Iron")) {
      barTexture = Identifier.fromNamespaceAndPath("minecraft", "block/iron_bars");
      postTexture = barTexture;
    }
    else {
      barTexture = Identifier.fromNamespaceAndPath(reg.getModid(), "block/palettes/metal_bars/" + base);
      postTexture = Identifier.fromNamespaceAndPath(reg.getModid(), post);
    }

    // for lambda stuff, must be final
    bartex = barTexture;
    postex = postTexture;

    var block = reg.block(base + suf, DecoBarsBlock::new)
      .properties(props -> props.noOcclusion().strength(5, 6)
        .requiresCorrectToolForDrops()
        .sound(SoundType.NETHERITE_BLOCK))
      .blockstate((ctx, prov)-> BlockStateGenerator.bar(base, suf, bartex, postex, ctx, prov))
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .tag(CreateDecoTags.BARS)
      .item()
        .model((ctx, prov) -> BlockStateGenerator.barItem(base, suf, bartex, ctx, prov))
        .properties(p -> (metal.equals("Netherite")) ? p.fireResistant() : p)
        .build();

    if (!suffix.equals("overlay")) {
      block = block.tag(AllTags.AllBlockTags.FAN_TRANSPARENT.tag);
    }

    return block;
  }

  public static <T extends Block> void recipeStonecutting (
          Supplier<Item> ingot, DataGenContext<Block, T> ctx, RegistrateRecipeProvider prov
  ) {
    SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingot.get()), RecipeCategory.DECORATIONS, ctx.get(), 4)
            .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(null, ingot.get()).build()
            ))
            .save(prov, ctx.getName() + "_from_stonecutting");

  }

  public static <T extends Block> void recipeCrafting (
          Supplier<Item> ingot,
          DataGenContext<Block, T> ctx, RegistrateRecipeProvider prov
  ) {
    ShapedRecipeBuilder.shaped(null, RecipeCategory.DECORATIONS, ctx.get(), 16)
            .pattern("bbb")
            .pattern("bbb")
            .define('b', ingot.get())
            .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ingot.get()
            ))
            .save(prov, ctx.getName());
  }

  public static <T extends Block> void recipeCraftingPanels (
          String metal,
          DataGenContext<Block, T> ctx, RegistrateRecipeProvider prov
  ) {

    ShapedRecipeBuilder.shaped(null, RecipeCategory.DECORATIONS, ctx.get(), 16)
            .pattern("ppp")
            .pattern("ppp")
            .define('p', CreateDecoTags.plate(metal))
            .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(null, CreateDecoTags.plate(metal)).build()
            ))
            .save(prov, ctx.getName());
  }
}
