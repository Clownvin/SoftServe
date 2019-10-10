package com.clownvin.softserve.cache;

import java.util.Map;
import java.util.function.Supplier;

public abstract class Cache<K, T> {
    protected final Map<K, Supplier<T>> cache;

    public Cache(Map<K, Supplier<T>> cache) {
        this.cache = cache;
    }

    public abstract T get(K identifier);
}
