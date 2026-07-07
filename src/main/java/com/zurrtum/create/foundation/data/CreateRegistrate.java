package com.zurrtum.create.foundation.data;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.zurrtum.create.client.foundation.block.connected.ConnectedTextureBehaviour;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class CreateRegistrate {
    private final String modid;
    private final Map<String, Block> pendingBlocks = new HashMap<>();
    private final List<ItemEntry<? extends Item>> items = new ArrayList<>();
    private final List<BlockEntry<? extends Block>> blocks = new ArrayList<>();

    protected CreateRegistrate(String modid) {
        this.modid = modid;
    }

    public static CreateRegistrate create(String modid) {
        return new CreateRegistrate(modid);
    }

    public String getModid() {
        return modid;
    }

    public Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(modid, path);
    }

    public void register() {
    }

    public CreateRegistrate defaultCreativeTab(String key) {
        return this;
    }

    public CreateRegistrate defaultCreativeTab(ResourceKey<CreativeModeTab> key) {
        return this;
    }

    public void addLang(String type, Identifier id, String value) {
    }

    public Collection<? extends RegistryEntry<?, ?>> getAll(ResourceKey<? extends Registry<Item>> key) {
        return items;
    }

    public void trackItem(ItemEntry<? extends Item> entry) {
        items.add(entry);
    }

    public void trackBlock(BlockEntry<? extends Block> entry) {
        blocks.add(entry);
    }

    public void rememberBlock(String name, Block block) {
        pendingBlocks.put(name, block);
    }

    public Block blockByName(String name) {
        return pendingBlocks.get(name);
    }

    public static NonNullConsumer<? super Block> connectedTextures(Supplier<ConnectedTextureBehaviour> behavior) {
        return block -> {};
    }

    public static <T extends Block> NonNullConsumer<? super T> blockModel(Supplier<?> func) {
        return block -> {};
    }
}
