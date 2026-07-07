package com.tterrag.registrate.util.entry;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntry<T extends Block> extends RegistryEntry<Block, T> implements ItemLike {
    public BlockEntry(String name, T value) {
        super(name, value);
    }

    public BlockEntry(Identifier id, T value) {
        super(id, value);
    }

    @Override
    public Item asItem() {
        return value.asItem();
    }

    public ItemStack asStack() {
        return new ItemStack(asItem());
    }

    public BlockState getDefaultState() {
        return value.defaultBlockState();
    }

    public BlockState defaultBlockState() {
        return value.defaultBlockState();
    }

    public boolean has(BlockState state) {
        return state.is(value);
    }
}
