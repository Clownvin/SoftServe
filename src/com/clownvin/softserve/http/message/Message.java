package com.clownvin.softserve.http.message;

import java.util.Map;

public abstract class Message {

    protected final Map<String, String> headers;

    protected Message(final Map<String, String> headers) {
        this.headers = headers;
    }
}
