package com.kevintang.model.entities;

public class Player extends Entity{

    public static final char SYMBOL = '$';

    public Player(String name, int x, int y) {
        super(SYMBOL, name, x, y);
    }

    @Override
    public void encounter(Entity entity) {

    }

    @Override
    public void interaction(Entity entity) {

    }
}
