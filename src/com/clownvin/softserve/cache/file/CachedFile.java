package com.clownvin.softserve.cache.file;

import com.clownvin.softserve.cache.CacheItem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CachedFile implements CacheItem<byte[]> {

    protected volatile long lastModified;

    protected final File file;
    protected volatile byte[] bytes;

    public CachedFile(File file) throws IOException {
        this(file, Files.readAllBytes(file.toPath()));
    }

    public CachedFile(File file, byte[] bytes) {
        this.file = file;
        this.bytes = bytes;
        this.lastModified = file.lastModified();
    }

    @Override
    public byte[] get() {
        if (file.lastModified() > lastModified) {
            try {
                bytes = Files.readAllBytes(file.toPath());
                lastModified = file.lastModified();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return bytes;
    }
}
