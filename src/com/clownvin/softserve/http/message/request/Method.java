package com.clownvin.softserve.http.message.request;

public enum Method {
    GET, POST, PUT, DELETE;

    public static Method parse(String method) {
        switch (method) {
            case "GET":
                return GET;
            case "POST":
                return POST;
            case "PUT":
                return PUT;
            case "DELETE":
                return DELETE;
            default:
                throw new RuntimeException("Malformed Method: " + method);
        }
    }
}
