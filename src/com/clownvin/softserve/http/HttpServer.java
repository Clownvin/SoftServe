package com.clownvin.softserve.http;

import com.clownvin.softserve.route.Router;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    protected final Router routes;
    protected final int port;
    protected final ExecutorService executor;
    protected volatile boolean started = false;

    public HttpServer(int port, Router routes) {
        this(port, routes, Executors.newCachedThreadPool());
    }

    public HttpServer(int port, Router routes, ExecutorService executor) {
        this.port = port;
        this.routes = routes;
        this.executor = executor;
    }

    public Router getRoutes() {
        return routes;
    }

    public void start() {
        if (started) {
            throw new IllegalStateException("Server already started!");
        }
        started = true;
        try {
            ServerSocket socket = new ServerSocket(port);
            System.out.println("\uD83C\uDF7B HTTP Server listening on port: " + port + " \uD83C\uDF7B");
            while (true) {
                Connection connection = new Connection(this, socket.accept());
                executor.submit(connection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        started = false;
    }
}
