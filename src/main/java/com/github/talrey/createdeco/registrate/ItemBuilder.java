package com.github.talrey.createdeco.registrate;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.zurrtum.create.foundation.data.CreateDecoRegistrate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.UnaryOperator;

public class ItemBuilder<T extends Item, P> {
    private final CreateDecoRegistrate owner;
    private final String name;
    private final NonNullFunction<Item.Properties, T> factory;
    private final P parent;
    private UnaryOperator<Item.Properties> properties = UnaryOperator.identity();

    public ItemBuilder(CreateDecoRegistrate owner, String name, NonNullFunction<Item.Properties, T> factory) {
        this(owner, name, factory, null);
    }

    public ItemBuilder(CreateDecoRegistrate owner, String name, NonNullFunction<Item.Properties, T> factory, P parent) {
        this.owner = owner;
        this.name = name;
        this.factory = factory;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public ItemBuilder<T, P> properties(UnaryOperator<Item.Properties> operator) {
        UnaryOperator<Item.Properties> previous = properties;
        properties = props -> operator.apply(previous.apply(props));
        return this;
    }

    public ItemBuilder<T, P> recipe(NonNullBiConsumer<DataGenContext<Item, T>, RegistrateRecipeProvider> consumer) {
        return this;
    }

    public ItemBuilder<T, P> model(NonNullBiConsumer<DataGenContext<Item, T>, RegistrateItemModelProvider> consumer) {
        return this;
    }

    @SafeVarargs
    public final ItemBuilder<T, P> tag(TagKey<Item>... tags) {
        return this;
    }

    public ItemBuilder<T, P> lang(String name) {
        return this;
    }

    public P build() {
        return parent;
    }

    T create(Item.Properties props) {
        return factory.apply(properties.apply(props));
    }

    public ItemEntry<T> register() {
        Identifier id = owner.id(name);
        Item.Properties props = properties.apply(new Item.Properties()
                .setId(ResourceKey.create(Registries.ITEM, id)));
        T item = factory.apply(props);
        Registry.register(BuiltInRegistries.ITEM, id, item);
        ItemEntry<T> entry = new ItemEntry<>(id, item);
        owner.trackEntry(Registries.ITEM, entry);
        return entry;
    }
}
