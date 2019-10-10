package com.clownvin.softserve.http;

public enum Version {
    HTTP1_1("HTTP/1.1");

    final String versionString;

    Version(String versionString) {
        this.versionString = versionString;
    }

    public static Version parse(String s) {
        switch (s) {
            default:
                return HTTP1_1;
        }
    }

    public String toString() {
        return versionString;
    }
}
