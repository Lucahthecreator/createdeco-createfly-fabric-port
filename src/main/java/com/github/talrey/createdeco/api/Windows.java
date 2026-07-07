package com.github.talrey.createdeco.api;

import com.github.talrey.createdeco.BlockStateGenerator;
import com.github.talrey.createdeco.CreateDecoMod;
import com.github.talrey.createdeco.connected.SpriteShifts;
import com.zurrtum.create.content.decoration.palettes.ConnectedGlassPaneBlock;
import com.zurrtum.create.content.decoration.palettes.GlassPaneBlock;
import com.zurrtum.create.content.decoration.palettes.WindowBlock;
import com.zurrtum.create.client.foundation.block.connected.CTSpriteShiftEntry;
import com.zurrtum.create.client.foundation.block.connected.GlassPaneCTBehaviour;
import com.zurrtum.create.client.foundation.block.connected.HorizontalCTBehaviour;
import com.zurrtum.create.foundation.data.CreateDecoRegistrate;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.DataIngredient;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.BlockPos;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.common.Tags;

import java.util.Locale;
import java.util.function.Supplier;

import static com.zurrtum.create.foundation.data.CreateDecoRegistrate.connectedTextures;

public class Windows {
  private static BlockBehaviour.Properties glassProperties(BlockBehaviour.Properties p) {
    return p.isValidSpawn(Windows::never)
        .isRedstoneConductor(Windows::never)
        .isSuffocating(Windows::never)
        .isViewBlocking(Windows::never);
  }

  private static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
    return false;
  }

  private static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
    return false;
  }

  public static BlockEntry<WindowBlock> metalWindowBlock(String metal) {
    return metalWindowBlock(metal, () -> null, false);
  }

  public static BlockEntry<WindowBlock> metalWindowBlock(String metal,
                                                          Supplier<Supplier<?>> renderType, boolean translucent) {
    String name = metal + "_window";
    NonNullFunction<String, Identifier> end_texture = $ -> CreateDecoMod.id(palettesDir() + $);
    NonNullFunction<String, Identifier> side_texture = n -> CreateDecoMod.id(palettesDir() + n);
    return windowBlock(name, () -> SpriteShifts.METAL_WINDOWS.get(metal), renderType,
        translucent, end_texture, side_texture, Blocks.GLASS::defaultMapColor);
  }


  public static BlockEntry<WindowBlock> windowBlock(String name,
                                                    Supplier<CTSpriteShiftEntry> ct, Supplier<Supplier<?>> renderType, boolean translucent,
                                                    NonNullFunction<String, Identifier> endTexture, NonNullFunction<String, Identifier> sideTexture,
                                                    Supplier<MapColor> color) {
    return CreateDecoMod.REGISTRATE.block(name.toLowerCase(Locale.ROOT).replace(" ", "_"), p -> new WindowBlock(p, translucent))
      //.onRegister(connectedTextures(() -> new HorizontalCTBehaviour(ct.get())))
      .onRegister(CreateDecoRegistrate.connectedTextures(() ->
        new HorizontalCTBehaviour(SpriteShifts.METAL_WINDOWS.get(name.replace("_window", ""))
      )))
      .addLayer(renderType)
      .recipe((c, p) -> {})
      .initialProperties(() -> Blocks.GLASS)
      .properties(Windows::glassProperties)
      .properties(p -> p.mapColor(color.get()))
      .loot((t, g) -> t.dropWhenSilkTouch(g))
      .blockstate((ctx,prov)-> BlockStateGenerator.window(ctx, prov, sideTexture, endTexture))
      .tag(BlockTags.IMPERMEABLE)
      .tag(Tags.Blocks.GLASS_BLOCKS)
      .simpleItem()
      .register();
  }

  public static BlockEntry<ConnectedGlassPaneBlock> metalWindowPane(String metal,
                                                                     Supplier<? extends Block> parent) {
    return metalWindowPane(metal, parent, () -> null);
  }

  public static BlockEntry<ConnectedGlassPaneBlock> metalWindowPane(String metal,
                                                                     Supplier<? extends Block> parent, Supplier<Supplier<?>> renderType) {
    String name = metal.toLowerCase(Locale.ROOT).replace(" ", "_") + "_window";
    Identifier topTexture = CreateDecoMod.id(palettesDir() + name + "_end");
    Identifier sideTexture = CreateDecoMod.id(palettesDir() + name);
    return connectedGlassPane(name, parent, () -> SpriteShifts.METAL_WINDOWS.get(metal), sideTexture,
        sideTexture, topTexture, renderType);
  }

  private static BlockEntry<ConnectedGlassPaneBlock> connectedGlassPane(String name, Supplier<? extends Block> parent,
                                                                        Supplier<CTSpriteShiftEntry> ctshift, Identifier sideTexture, Identifier itemSideTexture,
                                                                        Identifier topTexture, Supplier<Supplier<?>> renderType) {
    NonNullConsumer<? super ConnectedGlassPaneBlock> connectedTextures =
        connectedTextures(() -> new GlassPaneCTBehaviour(ctshift.get()));
    String CGPparents = "block/connected_glass_pane/";
    String prefix = name + "_pane_";

    return glassPane(name, parent, itemSideTexture, topTexture, ConnectedGlassPaneBlock::new, renderType,
        connectedTextures, BlockStateGenerator.windowPane(CGPparents, prefix, sideTexture, topTexture));
  }

  private static <G extends GlassPaneBlock> BlockEntry<G> glassPane(String name, Supplier<? extends Block> parent,
                                                                    Identifier sideTexture, Identifier topTexture, NonNullFunction<BlockBehaviour.Properties, G> factory,
                                                                    Supplier<Supplier<?>> renderType, NonNullConsumer<? super G> connectedTextures,
                                                                    NonNullBiConsumer<DataGenContext<Block, G>, RegistrateBlockstateProvider> stateProvider) {
    name += "_pane";

    return CreateDecoMod.REGISTRATE.block(name, factory)
      .onRegister(connectedTextures)
      .addLayer(renderType)
      .initialProperties(() -> Blocks.GLASS_PANE)
      .properties(p -> p.mapColor(parent.get()
        .defaultMapColor()))
      .blockstate(stateProvider)
      .tag(Tags.Blocks.GLASS_PANES)
      .recipe((c, p) -> ShapedRecipeBuilder.shaped(null, RecipeCategory.BUILDING_BLOCKS, c.get(), 16)
        .pattern("###")
        .pattern("###")
        .define('#', parent.get())
        .unlockedBy("has_ingredient", RegistrateRecipeProvider.has(parent.get()))
        .save(p))
      .loot((t, g) -> t.dropWhenSilkTouch(g))
      .item()
      .model((c, p) -> p.withExistingParent(c.getName(), CreateDecoMod.id("item/pane"))
        .texture("pane", sideTexture)
        .texture("edge", topTexture))
      .build()
      .register();
  }

  private static String palettesDir() {
    return "block/palettes/windows/";
  }
}
