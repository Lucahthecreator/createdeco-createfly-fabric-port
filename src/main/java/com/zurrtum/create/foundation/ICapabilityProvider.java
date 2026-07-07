package com.zurrtum.create.foundation;

import java.util.function.Supplier;

public class ICapabilityProvider<T> implements Supplier<T> {
    private final Supplier<T> supplier;

    private ICapabilityProvider(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> ICapabilityProvider<T> of(Supplier<T> supplier) {
        return new ICapabilityProvider<>(supplier);
    }

    public static <T> ICapabilityProvider<T> of(T value) {
        return new ICapabilityProvider<>(() -> value);
    }

    @Override
    public T get() {
        return supplier.get();
    }

    public T getCapability() {
        return get();
    }
}
