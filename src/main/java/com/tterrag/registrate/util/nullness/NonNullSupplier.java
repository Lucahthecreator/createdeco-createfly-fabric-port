package com.tterrag.registrate.util.nullness;

import java.util.function.Supplier;

@FunctionalInterface
public interface NonNullSupplier<T> extends Supplier<T> {
}
