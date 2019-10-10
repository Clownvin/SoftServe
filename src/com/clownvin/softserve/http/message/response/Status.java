package com.clownvin.softserve.http.message.response;

import java.util.Hashtable;

public enum Status {
    _100(100, "Continue"), _101(101, "Switching Protocols"), _102(102, "Processing"),
    _200(200, "OK"), _201(201, "Created"), _202(202, "Accepted"), _203(203, "Non-Authoritative Information"), _204(204, "No Content"), _205(205, "Reset Content"), _206(206, "Partial Content"), _207(207, "Multi-Status"), _208(208, "Already Reported"), _226(226, "IM Used"),
    _300(300, "Multiple Choices"), _301(301, "Moved Permanently"), _302(302, "Found"), _303(303, "See Other"), _304(304, "Not Modified"), _305(305, "Use Proxy"), /*306 unused*/ _307(307, "Temporary Redirect"), _308(308, "Permanent Redirect"),
    _400(400, "Bad Request"), _401(401, "Unauthorized"), _402(402, "Payment Required"), _403(403, "Forbidden"), _404(404, "Not Found"), _405(405, "Method Not Allowed"), _406(406, "Not Acceptable"), _407(407, "Proxy Authentication Required"), _408(408, "Request Timeout"), _409(409, "Conflict"),
    _500(500, "Internal Server Error");

    private static Hashtable<Integer, Status> statusMap = new Hashtable<>();

    static {
        for (Status status : Status.values()) {
            statusMap.put(status.code, status);
        }
    }

    public final int code;
    public final String text;

    Status(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public static Status get(int code) {
        return statusMap.get(code);
    }

    public String getANSIColorCode() {
        if (this.code < 400) {
            return "\u001B[32m";
        }
        if (this.code < 500) {
            return "\u001B[33m";
        }
        return "\u001B[35m";
    }
}
