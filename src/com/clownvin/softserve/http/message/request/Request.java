package com.clownvin.softserve.http.message.request;

import com.clownvin.softserve.http.Version;
import com.clownvin.softserve.http.message.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Request extends Message {

    protected final Method method;
    protected final URL url;
    protected final Version version;
    protected final char[] body;

    protected Request(Method method, URL url, Version version, Map<String, String> headers, char[] body) {
        super(headers);
        this.method = method;
        this.url = url;
        this.version = version;
        this.body = body;
    }

    //TODO refactor
    public static Request read(BufferedReader in) throws IOException {
        String startLine = in.readLine();
        String[] startingSplit = startLine.split("\\s");
        if (startingSplit.length != 3) {
            throw new RuntimeException("Malformed Message Start: " + startLine);
        }
        System.out.println("\uD83D\uDCB0 Recieved: " + startLine + " \uD83D\uDCB0");
        Method method = Method.parse(startingSplit[0]);
        URL url = URL.parse(startingSplit[1]);
        Version version = Version.parse(startingSplit[2]);
        String currentLine = null;
        HashMap<String, String> headers = new HashMap<>();
        int bodyLength = -1;
        char[] body;
        do {
            currentLine = in.readLine();
            if (currentLine.length() == 0) {
                break;
            }
            String[] split = currentLine.split(":\\s");
            if (split.length != 2) {
                throw new RuntimeException("Malformed Header: " + currentLine);
            }
            if (split[0] == "Content-Length") {
                bodyLength = Integer.parseInt(split[1]);
            }
            headers.put(split[0], split[1]);
        } while (true);
        if (bodyLength != -1) {
            body = new char[bodyLength];
            in.read(body);
        } else {
            body = new char[0];
        }
        return new Request(method, url, version, headers, body);
    }

    public Method getMethod() {
        return method;
    }

    public URL getUrl() {
        return url;
    }

    public Version getVersion() {
        return version;
    }
}
