package com.github.talrey.createdeco.api;

import com.github.talrey.createdeco.BlockStateGenerator;
import com.github.talrey.createdeco.blocks.SupportWedgeBlock;
import com.zurrtum.create.foundation.data.CreateDecoRegistrate;
import com.github.talrey.createdeco.registrate.BlockBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.Locale;

public class Wedges {
  public static BlockBuilder<SupportWedgeBlock,?> build (
    CreateDecoRegistrate reg, String metal
  ) {
    String regName = metal.toLowerCase(Locale.ROOT).replaceAll(" ", "_") + "_support_wedge";

    return reg.block(regName, SupportWedgeBlock::new)
      .properties(props -> props.strength(5, 6)
        .requiresCorrectToolForDrops()
        .sound(SoundType.NETHERITE_BLOCK)
        .noOcclusion()
        .isViewBlocking((a, b, c) -> false)
        .isSuffocating((a, b, c) -> false)
      )
      .item()
      .build()
      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
      .blockstate((ctx, prov) -> BlockStateGenerator.supportWedge(reg, metal, ctx, prov))
      .lang(metal + " Support Wedge");
  }

  public static <T extends Block> void recipe (
          String metal,
          DataGenContext<Block, T> ctx, RegistrateRecipeProvider prov
  ) {

    ShapedRecipeBuilder.shaped(null, RecipeCategory.DECORATIONS, ctx.get(), 3)
        .pattern(" p")
        .pattern("pp")
        .define('p', CreateDecoTags.plate(metal))
        .unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(
            ItemPredicate.Builder.item().of(null, CreateDecoTags.plate(metal)).build()
        ))
        .save(prov);

  }
}
