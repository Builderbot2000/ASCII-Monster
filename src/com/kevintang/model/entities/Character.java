package com.kevintang.model.entities;

public class Character extends Entity {

    public static final char SYMBOL = 'C';

    public Character(String name, int x, int y) {
        super(SYMBOL, name, x, y);
    }

    @Override
    public void encounter(Entity entity) {

    }

    @Override
    public void interaction(Entity entity) {

    }
}
