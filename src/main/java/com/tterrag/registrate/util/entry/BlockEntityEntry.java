package com.tterrag.registrate.util.entry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityEntry<T extends BlockEntity> extends RegistryEntry<BlockEntityType<?>, BlockEntityType<T>> {
    public BlockEntityEntry(String name, BlockEntityType<T> value) {
        super(name, value);
    }

    public T get(BlockGetter level, BlockPos pos) {
        return value.getBlockEntity(level, pos);
    }
}
