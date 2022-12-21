package com.wojtas.calculator;

public class Calculator {
    private int x;
    private int y;

    public Calculator(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public int calculateSum() {
        return x+y;
    }

    public int calculateDistinction() {
        return x*y;
    }
}
