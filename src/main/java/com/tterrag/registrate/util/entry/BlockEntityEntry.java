package com.tterrag.registrate.util.entry;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityEntry<T extends BlockEntity> extends RegistryEntry<BlockEntityType<?>, BlockEntityType<T>> {
    public BlockEntityEntry(String name, BlockEntityType<T> value) {
        super(name, value);
    }

    public BlockEntityEntry(Identifier id, BlockEntityType<T> value) {
        super(id, value);
    }

    public T get(BlockGetter level, BlockPos pos) {
        return value.getBlockEntity(level, pos);
    }

    public T create(BlockPos pos, BlockState state) {
        return value.create(pos, state);
    }
}
