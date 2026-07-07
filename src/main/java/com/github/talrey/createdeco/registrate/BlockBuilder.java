package com.github.talrey.createdeco.registrate;

import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockLootProvider;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.zurrtum.create.foundation.data.CreateDecoRegistrate;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class BlockBuilder<T extends Block, P> {
    private final CreateDecoRegistrate owner;
    private final String name;
    private final NonNullFunction<BlockBehaviour.Properties, T> factory;
    private UnaryOperator<BlockBehaviour.Properties> properties = UnaryOperator.identity();
    private ItemBuilder<? extends BlockItem, BlockBuilder<T, P>> itemBuilder;
    private boolean simpleItem;
    private NonNullConsumer<? super T> onRegister = block -> {};
    private T registeredBlock;

    public BlockBuilder(CreateDecoRegistrate owner, String name, NonNullFunction<BlockBehaviour.Properties, T> factory) {
        this.owner = owner;
        this.name = name;
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public BlockBuilder<T, P> initialProperties(Supplier<? extends Block> block) {
        properties = ignored -> BlockBehaviour.Properties.ofFullCopy(block.get());
        return this;
    }

    public BlockBuilder<T, P> properties(UnaryOperator<BlockBehaviour.Properties> operator) {
        UnaryOperator<BlockBehaviour.Properties> previous = properties;
        properties = props -> operator.apply(previous.apply(props));
        return this;
    }

    public <R> R transform(NonNullFunction<BlockBuilder<T, P>, R> function) {
        return function.apply(this);
    }

    public BlockBuilder<T, P> blockstate(NonNullBiConsumer<DataGenContext<Block, T>, RegistrateBlockstateProvider> consumer) {
        return this;
    }

    public BlockBuilder<T, P> recipe(NonNullBiConsumer<DataGenContext<Block, T>, RegistrateRecipeProvider> consumer) {
        return this;
    }

    public BlockBuilder<T, P> loot(NonNullBiConsumer<RegistrateBlockLootProvider, T> consumer) {
        return this;
    }

    public BlockBuilder<T, P> defaultLoot() {
        return this;
    }

    public BlockBuilder<T, P> addLayer(Supplier<?> layer) {
        return this;
    }

    @SafeVarargs
    public final BlockBuilder<T, P> tag(TagKey<Block>... tags) {
        return this;
    }

    public BlockBuilder<T, P> lang(String name) {
        return this;
    }

    public BlockBuilder<T, P> onRegister(NonNullConsumer<? super T> consumer) {
        onRegister = consumer;
        return this;
    }

    public <R> BlockBuilder<T, P> onRegisterAfter(ResourceKey<? extends Registry<R>> registry, NonNullConsumer<? super T> consumer) {
        return this;
    }

    public ItemBuilder<BlockItem, BlockBuilder<T, P>> item() {
        itemBuilder = new ItemBuilder<>(owner, name, props -> new BlockItem(getRegisteredBlock(), props), this);
        return (ItemBuilder<BlockItem, BlockBuilder<T, P>>) itemBuilder;
    }

    public <I extends BlockItem> ItemBuilder<I, BlockBuilder<T, P>> item(NonNullFunction<Item.Properties, I> factory) {
        itemBuilder = new ItemBuilder<>(owner, name, factory, this);
        return (ItemBuilder<I, BlockBuilder<T, P>>) itemBuilder;
    }

    public <I extends BlockItem> ItemBuilder<I, BlockBuilder<T, P>> item(java.util.function.BiFunction<T, Item.Properties, I> factory) {
        itemBuilder = new ItemBuilder<>(owner, name, props -> factory.apply(getRegisteredBlock(), props), this);
        return (ItemBuilder<I, BlockBuilder<T, P>>) itemBuilder;
    }

    public BlockBuilder<T, P> simpleItem() {
        simpleItem = true;
        return this;
    }

    public BlockEntry<T> register() {
        Identifier id = owner.id(name);
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);
        BlockBehaviour.Properties props = properties.apply(BlockBehaviour.Properties.of()).setId(key);
        registeredBlock = factory.apply(props);
        Registry.register(BuiltInRegistries.BLOCK, id, registeredBlock);
        if (simpleItem || itemBuilder != null) {
            Item.Properties itemProps = new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id));
            Item item = itemBuilder == null ? new BlockItem(registeredBlock, itemProps) : itemBuilder.create(itemProps);
            Registry.register(BuiltInRegistries.ITEM, id, item);
            owner.trackEntry(Registries.ITEM, new ItemEntry<>(id, item));
        }
        onRegister.accept(registeredBlock);
        BlockEntry<T> entry = new BlockEntry<>(id, registeredBlock);
        owner.trackEntry(Registries.BLOCK, entry);
        return entry;
    }

    private T getRegisteredBlock() {
        return registeredBlock;
    }
}
