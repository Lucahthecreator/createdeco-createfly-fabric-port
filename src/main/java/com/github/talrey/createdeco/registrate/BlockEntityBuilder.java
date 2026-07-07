package com.github.talrey.createdeco.registrate;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.zurrtum.create.foundation.data.CreateDecoRegistrate;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Arrays;

public class BlockEntityBuilder<T extends BlockEntity, P> {
    @FunctionalInterface
    public interface BlockEntityFactory<T extends BlockEntity> {
        T create(BlockEntityType<T> type, BlockPos pos, BlockState state);
    }

    private final CreateDecoRegistrate owner;
    private final String name;
    private final BlockEntityFactory<T> factory;
    private Block[] validBlocks = new Block[0];

    public BlockEntityBuilder(CreateDecoRegistrate owner, String name, BlockEntityFactory<T> factory) {
        this.owner = owner;
        this.name = name;
        this.factory = factory;
    }

    @SafeVarargs
    public final BlockEntityBuilder<T, P> validBlocks(BlockEntry<? extends Block>... entries) {
        validBlocks = Arrays.stream(entries).map(BlockEntry::get).toArray(Block[]::new);
        return this;
    }

    public BlockEntityBuilder<T, P> renderer(java.util.function.Supplier<java.util.function.Function<?, ?>> renderer) {
        return this;
    }

    public BlockEntityEntry<T> register() {
        Identifier id = owner.id(name);
        BlockEntityType<T>[] holder = new BlockEntityType[1];
        BlockEntityType<T> type = FabricBlockEntityTypeBuilder.create(
                (pos, state) -> factory.create(holder[0], pos, state),
                validBlocks
        ).build();
        holder[0] = type;
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, ResourceKey.create(Registries.BLOCK_ENTITY_TYPE, id), type);
        BlockEntityEntry<T> entry = new BlockEntityEntry<>(id, type);
        owner.trackEntry(Registries.BLOCK_ENTITY_TYPE, entry);
        return entry;
    }
}
