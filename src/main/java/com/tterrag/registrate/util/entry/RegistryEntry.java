package com.tterrag.registrate.util.entry;

import java.util.function.Supplier;

public class RegistryEntry<R, T extends R> implements Supplier<T> {
    protected final String name;
    protected final T value;

    public RegistryEntry(String name, T value) {
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
}
