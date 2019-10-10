package com.clownvin.softserve.route;

import com.clownvin.softserve.http.message.request.Method;
import com.clownvin.softserve.http.message.request.Request;

public class PatternedMatcher implements RouteMatcher {

    protected final Method method;
    protected final String regex;

    public PatternedMatcher(Method method, String regex) {
        this.method = method;
        this.regex = regex;
    }

    @Override
    public boolean test(Request request) {
        return request.getMethod() == method && request.getUrl().getPath().matches(regex);
    }
}
