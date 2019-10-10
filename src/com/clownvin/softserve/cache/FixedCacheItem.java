package com.clownvin.softserve.cache;

public class FixedCacheItem<T> implements CacheItem<T> {

    protected final T item;

    public FixedCacheItem(final T item) {
        this.item = item;
    }

    @Override
    public T get() {
        return item;
    }
}
