package com.clownvin.softserve.cache.file;

import com.clownvin.softserve.cache.Cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.function.Supplier;

public class FileCache extends Cache<String, byte[]> {

    public FileCache() {
        super(new HashMap<>());
    }

    public void store(File file) throws IOException {
        store(file.getAbsolutePath(), file);
    }

    public void store(String identifier, File file) throws IOException {
        store(identifier, file, Files.readAllBytes(file.toPath()));
    }

    public void store(String identifier, File file, byte[] bytes) {
        cache.put(identifier, new CachedFile(file, bytes));
    }

    @Override
    public byte[] get(String identifier) {
        Supplier<byte[]> supplier = cache.get(identifier);
        if (supplier == null) {
            return null;
        }
        return supplier.get();
    }
}
