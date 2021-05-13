package com.kevintang.ui;

/**
 * A single ASCII symbol to be displayed on screen in console line output form
 */
public class Pixel {

    private char symbol;
    private int y;
    private int x;

    public Pixel(char symbol, int y, int x) {
        this.symbol = symbol;
        this.y = y;
        this.x = x;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
