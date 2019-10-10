package com.clownvin.softserve.http.message.response;

import com.clownvin.softserve.SoftServe;
import com.clownvin.softserve.http.Version;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.HashMap;

public class Response {
    protected final Version version;
    protected final Status status;
    protected final HashMap<String, String> headers;
    protected final byte[] body;

    protected Response(Version version, Status status, HashMap<String, String> headers, byte[] body) {
        this.version = version;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public Status getStatus() {
        return status;
    }

    public static Builder builder() {
        return new Builder();
    }

    protected static void writeString(OutputStream out, String string) throws IOException {
        for (char c : string.toCharArray()) {
            out.write(c);
        }
    }

    public static void send(OutputStream out, Response response) throws IOException {
        writeString(out, response.version.toString());
        out.write(' ');
        writeString(out, response.status.code + "");
        out.write(' ');
        writeString(out, response.status.text);
        writeString(out, "\r\n");
        for (String header : response.headers.keySet()) {
            writeString(out, header + ": " + response.headers.get(header) + "\r\n");
        }
        writeString(out, "\r\n");
        out.write(response.body);
        out.flush();
    }

    public static class Builder {
        Version version = Version.HTTP1_1;
        Status status = Status._200;
        HashMap<String, String> headers = new HashMap<>();
        byte[] body;

        private Builder() {

        }

        public Builder status(int status) {
            this.status = Status.get(status);
            return this;
        }

        public Builder body(String body) {
            return body(body, "text/plain");
        }

        public Builder body(String body, String mimeType) {
            ByteBuffer encoded = StandardCharsets.UTF_8.encode(body);
            byte[] bytes = new byte[encoded.limit()];
            encoded.get(bytes);
            return body(bytes, mimeType, "; charset=utf-8");
        }

        public Builder body(byte[] body, String mimeType) {
            return body(body, mimeType, "; charset=utf-8");
        }

        public Builder body(byte[] body, String mimeType, String charset) {
            this.body = body;
            headers.put("Content-Length", "" + this.body.length);
            headers.put("Content-Type", mimeType + charset);
            return this;
        }

        public Response finish() {
            headers.put("Date", Calendar.getInstance().getTime().toString());
            headers.put("Server", "SoftServe " + SoftServe.VERSION + " (" + SoftServe.OS_INFO +")");
            return new Response(version, status, headers, body);
        }
    }
}
