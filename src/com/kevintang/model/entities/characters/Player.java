package com.kevintang.model.entities.characters;

import com.kevintang.model.entities.Entity;

/**
 * The player character
 */
public class Player extends Entity {

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
