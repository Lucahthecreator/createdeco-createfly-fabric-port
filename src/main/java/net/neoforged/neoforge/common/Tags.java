package net.neoforged.neoforge.common;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class Tags {
    public static class Blocks {
        public static final TagKey<Block> GLASS_BLOCKS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("c", "glass_blocks"));
        public static final TagKey<Block> GLASS_PANES = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("c", "glass_panes"));
    }

    public static class Items {
        public static final TagKey<Item> GLASS_BLOCKS_COLORLESS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", "glass_blocks/colorless"));
    }
}
