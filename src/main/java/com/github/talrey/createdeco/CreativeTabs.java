package com.github.talrey.createdeco;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.function.Supplier;

public class CreativeTabs {
  public static final String PROPS_KEY = "props_tab";
  public static final String BRICKS_KEY = "bricks_tab";

  public static final ResourceKey<CreativeModeTab> PROPS_RESOURCE_KEY =
    ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), CreateDecoMod.id(PROPS_KEY));
  public static final ResourceKey<CreativeModeTab> BRICKS_RESOURCE_KEY =
    ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), CreateDecoMod.id(BRICKS_KEY));

  private static CreativeModeTab props;
  private static CreativeModeTab bricks;

  public static void register() {
    props = Registry.register(
      BuiltInRegistries.CREATIVE_MODE_TAB,
      PROPS_RESOURCE_KEY.identifier(),
      group(PROPS_KEY, () -> BlockRegistry.GREEN_CAGE_LAMPS.get("Brass").asStack(), false, 1)
      );
      bricks = Registry.register(
        BuiltInRegistries.CREATIVE_MODE_TAB,
        BRICKS_RESOURCE_KEY.identifier(),
        group(BRICKS_KEY, () -> BlockRegistry.BRICKS.get(DyeColor.LIGHT_BLUE).get("blue_bricks").asStack(), true, 2)
      );
    }

  public static CreativeModeTab props() {
    return props;
  }

  public static CreativeModeTab bricks() {
    return bricks;
  }

    private static CreativeModeTab group(String name, Supplier<ItemStack> item, boolean bricksTab, int column) {
      return CreativeModeTab.builder(CreativeModeTab.Row.TOP, column)
        .icon(item)
        .title(Component.translatableWithFallback(
           "itemGroup." + CreateDecoMod.MOD_ID + "." + name,
           bricksTab ? "Create Deco Bricks" : "Create Deco Props"
        ))
        .displayItems((params, output) -> CreateDecoMod.REGISTRATE.getAll(Registries.ITEM).stream()
          .map(entry -> (Item) entry.get())
          .sorted(Comparator
            .comparingInt((Item stackItem) -> categoryRank(BuiltInRegistries.ITEM.getKey(stackItem).getPath(), bricksTab))
            .thenComparingInt(stackItem -> materialRank(BuiltInRegistries.ITEM.getKey(stackItem).getPath()))
            .thenComparing(stackItem -> BuiltInRegistries.ITEM.getKey(stackItem).getPath()))
          .forEach(stackItem -> {
            String id = BuiltInRegistries.ITEM.getKey(stackItem).getPath();
            boolean isBrick = id.contains("brick") || id.equals("worn_brick");
            if (isBrick == bricksTab)
              output.accept(stackItem);
          }))
        .build();
    }

    private static int categoryRank(String id, boolean bricksTab) {
      if (bricksTab) {
        return brickFamilyRank(id) * 100 + brickStyleRank(id) * 10 + brickShapeRank(id);
      }

      if (id.endsWith("_sheet") || id.endsWith("_ingot") || id.endsWith("_nugget")) return 0;
      if (id.endsWith("_coin") || id.endsWith("_coinstack")) return 5;
      if (id.endsWith("_lamp")) return 10;
      if (id.endsWith("_placard")) return 20;
      if (id.endsWith("_bars")) return 30;
      if (id.endsWith("_bars_overlay")) return 31;
      if (id.endsWith("_catwalk")) return 40;
      if (id.endsWith("_catwalk_stairs")) return 41;
      if (id.endsWith("_catwalk_railing")) return 42;
      if (id.endsWith("_mesh_fence")) return 50;
      if (id.endsWith("_door") || id.endsWith("_trapdoor")) return 60;
      if (id.endsWith("_window") || id.endsWith("_window_pane")) return 70;
      if (id.endsWith("_ladder")) return 80;
      if (id.endsWith("_support") || id.endsWith("_support_wedge")) return 81;
      if (id.endsWith("_sheet_metal") || id.endsWith("_hull")) return 82;
      if (id.endsWith("_shipping_container")) return 90;
      return 100;
    }

    private static int brickFamilyRank(String id) {
      String[] families = {
        "red", "blue", "dean", "dusk", "pearl", "scarlet", "umber", "verdant", "worn"
      };
      for (int i = 0; i < families.length; i++) {
        String family = families[i];
        if (id.equals(family + "_brick") || id.equals(family + "_bricks")
          || id.contains("_" + family + "_brick") || id.contains("_" + family + "_bricks"))
          return i;
      }
      return 100;
    }

    private static int brickStyleRank(String id) {
      String[] styles = { "cracked", "mossy", "long", "short", "tiled", "corner" };
      for (int i = 0; i < styles.length; i++) {
        if (id.startsWith(styles[i] + "_"))
          return i + 1;
      }
      return 0;
    }

    private static int brickShapeRank(String id) {
      if (id.endsWith("_bricks") || id.equals("worn_brick")) return 0;
      if (id.endsWith("_brick_slab")) return 1;
      if (id.endsWith("_brick_stairs")) return 2;
      if (id.endsWith("_brick_wall")) return 3;
      return 9;
    }

    private static int materialRank(String id) {
      String[] order = {
        "andesite", "zinc", "copper", "brass", "iron", "industrial_iron",
        "gold", "netherite", "white", "light_gray", "gray", "black", "brown",
        "red", "orange", "yellow", "lime", "green", "cyan", "light_blue",
        "blue", "purple", "magenta", "pink"
      };
      for (int i = 0; i < order.length; i++) {
        if (id.equals(order[i]) || id.startsWith(order[i] + "_"))
          return i;
      }
      return 100;
    }
}
