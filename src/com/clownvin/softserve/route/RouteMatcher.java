package com.clownvin.softserve.route;

import com.clownvin.softserve.http.message.request.Request;

import java.util.HashMap;
import java.util.function.Predicate;

public interface RouteMatcher extends Predicate<Request> {

    static HashMap<String, String> getParams(Request request, String parameterizedRoute) {
        HashMap<String, String> params = new HashMap<>();
        String[] routeStrings = parameterizedRoute.split("/");
        String[] requestStrings = request.getUrl().getPath().split("/");
        for (int i = 0; i < routeStrings.length; i++) {
            if (!routeStrings[i].startsWith(":")) {
                continue;
            }
            params.put(routeStrings[i].substring(1), requestStrings[i]);
        }
        return params;
    }
}
