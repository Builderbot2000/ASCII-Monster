package com.kevintang.model.entities;

public class Player extends Character{

    public static final char SYMBOL = '$';

    public Player(String name, int x, int y) {
        super(SYMBOL, name, x, y);
    }
}
