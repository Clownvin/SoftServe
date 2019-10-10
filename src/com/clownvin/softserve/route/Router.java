package com.clownvin.softserve.route;

import com.clownvin.softserve.http.message.request.Request;
import com.clownvin.softserve.http.message.response.Response;

import java.util.ArrayList;
import java.util.Collection;

public class Router {

    protected final Collection<Route> routes;

    protected Router(Collection<Route> routes) {
        this.routes = routes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Response handle(Request request) {
        for (Route route : routes) {
            if (!route.matches(request)) {
                continue;
            }
            return route.handle(request);
        }
        return Response.builder().status(404).finish();
    }

    public static class Builder {
        private ArrayList<Route> routes = new ArrayList<>();

        private Builder() {
        }

        public Builder add(Route... routes) {
            for (Route route : routes) {
                this.routes.add(route);
            }
            return this;
        }

        public Router finish() {
            return new Router(routes);
        }
    }
}
