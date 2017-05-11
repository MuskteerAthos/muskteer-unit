/**
 * FileName:   Pair.java
 * @Description seq pair
 * All rights Reserved, Code by Muskteer
 * Copyright MuskteerAthos@gmail.com
 * @author MuskteerAthos
 */
package com.muskteer.unit;

public class Pair {
    Object K;
    Object V;
    Object J;
    Object L;

    public Object getL() {
        return L;
    }

    public void setL(Object l) {
        L = l;
    }

    public Object getJ() {
        return J;
    }

    public void setJ(Object j) {
        J = j;
    }

    public Object getK() {
        return K;
    }

    public void setK(Object k) {
        K = k;
    }

    public Object getV() {
        return V;
    }

    public void setV(Object v) {
        V = v;
    }

    public Pair(Object k, Object v) {
        super();
        K = k;
        V = v;
    }

    public Pair(Object k, Object v, Object j) {
        super();
        K = k;
        V = v;
        J = j;
    }

    public Pair(Object k, Object v, Object j, Object l) {
        super();
        K = k;
        V = v;
        J = j;
        L = l;
    }

    public Pair(Pair p, Object j) {
        super();
        K = p.getK();
        V = p.getV();
        J = j;
    }

    public Pair(Object k, Pair p) {
        super();
        K = k;
        V = p.getK();
        J = p.getV();
    }

    public Pair(Object k, Object v, Pair p) {
        super();
        K = k;
        V = v;
        J = p.getV();
        L = p.getK();
    }

    @Override
    public String toString() {
        return "Pair [K=" + K + ", V=" + V + ", J=" + J + ", L=" + L + "]";
    }

}
