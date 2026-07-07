package com.zurrtum.create.foundation.data;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

public class TagGen {
    public static <T extends Block, P> NonNullFunction<BlockBuilder<T, P>, BlockBuilder<T, P>> pickaxeOnly() {
        return builder -> builder.tag(BlockTags.MINEABLE_WITH_PICKAXE);
    }
}
