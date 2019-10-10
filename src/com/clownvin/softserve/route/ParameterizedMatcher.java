package com.clownvin.softserve.route;

import com.clownvin.softserve.http.message.request.Method;
import com.clownvin.softserve.http.message.request.Request;

public class ParameterizedMatcher implements RouteMatcher {

    protected final int slashes;
    protected final Method method;
    protected final String path;

    public ParameterizedMatcher(Method method, String fullPath) {
        this.method = method;
        this.slashes = countSlashes(fullPath);
        String[] split = fullPath.split("/:");
        this.path = split[0].toLowerCase();
    }

    protected static int countSlashes(String string) {
        int count = 0;
        for (char c : string.toCharArray()) {
            if (c == '/') {
                count++;
            }
        }
        return count;
    }

    @Override
    public boolean test(Request request) {
        return request.getMethod() == method && request.getUrl().getPath().toLowerCase().contains(path);
    }
}
