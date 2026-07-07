package com.zurrtum.create;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

public class AllTags {
    public enum AllBlockTags {
        FAN_TRANSPARENT(TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("create", "fan_transparent")));

        public final TagKey<Block> tag;

        AllBlockTags(TagKey<Block> tag) {
            this.tag = tag;
        }
    }
}
