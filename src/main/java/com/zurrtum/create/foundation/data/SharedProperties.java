package com.zurrtum.create.foundation.data;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.WeatheringCopper;

public class SharedProperties {
    public static Block wooden() {
        return Blocks.STRIPPED_SPRUCE_WOOD;
    }

    public static Block stone() {
        return Blocks.ANDESITE;
    }

    public static Block softMetal() {
        return Blocks.GOLD_BLOCK;
    }

    public static Block copperMetal() {
        return pickCopperBlock(Blocks.COPPER_BLOCK);
    }

    public static Block netheriteMetal() {
        return Blocks.NETHERITE_BLOCK;
    }

    public static Item asItem(Object blockOrCollection) {
        return pickCopperBlock(blockOrCollection).asItem();
    }

    private static Block pickCopperBlock(Object value) {
        if (value instanceof Block block) {
            return block;
        }
        try {
            Object weathering = value.getClass().getMethod("weathering").invoke(value);
            Object unaffected = weathering.getClass()
                    .getMethod("pick", WeatheringCopper.WeatherState.class)
                    .invoke(weathering, WeatheringCopper.WeatherState.UNAFFECTED);
            if (unaffected instanceof Block block) {
                return block;
            }
        } catch (ReflectiveOperationException ignored) {
        }
        return Blocks.GOLD_BLOCK;
    }
}
