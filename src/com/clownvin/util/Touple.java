package com.clownvin.util;

public class Touple<A, B> {
    public final A a;
    public final B b;

    public Touple(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public A getA() {
        return a;
    }

    public B getB() {
        return b;
    }
}
