package com.tterrag.registrate.util.entry;

import net.minecraft.resources.Identifier;

import java.util.function.Supplier;

public class RegistryEntry<R, T extends R> implements Supplier<T> {
    protected final Identifier id;
    protected final String name;
    protected final T value;

    public RegistryEntry(String name, T value) {
        this(null, name, value);
    }

    public RegistryEntry(Identifier id, T value) {
        this(id, id.getPath(), value);
    }

    private RegistryEntry(Identifier id, String name, T value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Identifier getId() {
        return id;
    }

    public boolean is(R value) {
        return this.value == value;
    }
}
