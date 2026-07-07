package com.tterrag.registrate.util.entry;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ItemEntry<T extends Item> extends RegistryEntry<Item, T> implements ItemLike {
    public ItemEntry(String name, T value) {
        super(name, value);
    }

    @Override
    public Item asItem() {
        return value;
    }

    public ItemStack asStack() {
        return new ItemStack(value);
    }
}
