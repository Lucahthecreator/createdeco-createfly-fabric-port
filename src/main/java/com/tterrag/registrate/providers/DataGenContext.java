package com.tterrag.registrate.providers;

public class DataGenContext<R, T extends R> {
    private final String name;
    private final T entry;

    public DataGenContext(String name, T entry) {
        this.name = name;
        this.entry = entry;
    }

    public String getName() {
        return name;
    }

    public T get() {
        return entry;
    }
}
