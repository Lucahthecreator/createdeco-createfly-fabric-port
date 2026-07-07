package com.zurrtum.create.foundation.data;

import com.github.talrey.createdeco.registrate.BlockBuilder;
import com.github.talrey.createdeco.registrate.BlockEntityBuilder;
import com.github.talrey.createdeco.registrate.ItemBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.zurrtum.create.client.foundation.block.connected.ConnectedTextureBehaviour;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CreateDecoRegistrate extends CreateRegistrate {
    private final String modid;
    private final Map<ResourceKey<?>, Collection<RegistryEntry<?, ?>>> entries = new HashMap<>();

    public CreateDecoRegistrate(String modid) {
        super(modid);
        this.modid = modid;
    }

    @Override
    public String getModid() {
        return modid;
    }

    @Override
    public Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(modid, path);
    }

    public <T extends Item> ItemBuilder<T, CreateDecoRegistrate> item(String name, NonNullFunction<Item.Properties, T> factory) {
        return new ItemBuilder<>(this, name, factory);
    }

    public <T extends Block> BlockBuilder<T, CreateDecoRegistrate> block(String name, NonNullFunction<BlockBehaviour.Properties, T> factory) {
        return new BlockBuilder<>(this, name, factory);
    }

    public <T extends BlockEntity> BlockEntityBuilder<T, CreateDecoRegistrate> blockEntity(String name, BlockEntityBuilder.BlockEntityFactory<T> factory) {
        return new BlockEntityBuilder<>(this, name, factory);
    }

    public void trackEntry(ResourceKey<? extends Registry<?>> key, RegistryEntry<?, ?> entry) {
        entries.computeIfAbsent(key, ignored -> new ArrayList<>()).add(entry);
    }

    @Override
    public Collection<? extends RegistryEntry<?, ?>> getAll(ResourceKey<? extends Registry<Item>> key) {
        return entries.getOrDefault(key, List.of());
    }

    public static NonNullConsumer<? super Block> connectedTextures(Supplier<ConnectedTextureBehaviour> behavior) {
        return block -> {};
    }

    public static <T extends Block> NonNullConsumer<? super T> blockModel(Supplier<?> func) {
        return block -> {};
    }
}
