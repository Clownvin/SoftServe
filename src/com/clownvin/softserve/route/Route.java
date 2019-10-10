package com.clownvin.softserve.route;

import com.clownvin.softserve.http.message.request.Request;
import com.clownvin.softserve.http.message.response.Response;

public class Route {
    protected final RouteMatcher matcher;
    protected final RouteHandler handler;

    public Route(RouteMatcher matcher, RouteHandler handler) {
        this.matcher = matcher;
        this.handler = handler;
    }

    public boolean matches(Request request) {
        return matcher.test(request);
    }

    public Response handle(Request request) {
        return handler.apply(request);
    }
}
