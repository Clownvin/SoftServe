package com.clownvin.softserve.http;

import com.clownvin.softserve.http.message.request.Request;
import com.clownvin.softserve.http.message.response.Response;
import com.clownvin.softserve.http.message.response.Status;
import com.clownvin.util.RandomEmoji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection implements Runnable {
    protected final HttpServer server;
    protected final Socket socket;

    public Connection(HttpServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        Status status = Status._500;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            Request request = Request.read(reader);
            Response response = server.getRoutes().handle(request);
            status = response.getStatus();
            Response.send(socket.getOutputStream(), response);
        } catch (IOException e) {
            try {
                status = Status._500;
                Response.send(socket.getOutputStream(), Response.builder().status(500).body("Internal Server Error: " + e.getMessage()).finish());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(RandomEmoji.emojify("Handled request in " + (System.currentTimeMillis() - start) + "ms: " + status.getANSIColorCode() + status.code + " " + status.text + "\u001B[0m") + '\n');
    }
}
