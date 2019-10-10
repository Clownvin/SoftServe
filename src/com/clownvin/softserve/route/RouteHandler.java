package com.clownvin.softserve.route;

import com.clownvin.softserve.http.message.request.Request;
import com.clownvin.softserve.http.message.response.Response;

import java.util.function.Function;

public interface RouteHandler extends Function<Request, Response> {
}
