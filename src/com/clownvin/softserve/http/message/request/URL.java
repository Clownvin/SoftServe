package com.clownvin.softserve.http.message.request;

import java.util.HashMap;
import java.util.Map;

public class URL {

    protected final String path;
    protected final Map<String, String> values;
    public URL(final String path, final Map<String, String> values) {
        this.path = path;
        this.values = values;
    }

    public static URL parse(String queryString) {
        int queryStart = queryString.indexOf("?");
        if (queryStart == -1) {
            return new URL(queryString, new HashMap<>());
        }
        String path = queryString.substring(0, queryStart);
        HashMap<String, String> values = new HashMap<>();
        String[] queries = queryString.substring(queryStart).split("&");
        for (String query : queries) {
            String[] keyValue = query.split("=");
            values.put(keyValue[0], keyValue[1]);
        }
        return new URL(path, values);
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getValues() {
        return values;
    }
}
