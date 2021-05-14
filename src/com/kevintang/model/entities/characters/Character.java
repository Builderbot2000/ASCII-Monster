package com.kevintang.model.entities.characters;

import com.kevintang.model.entities.Entity;

/**
 * Model representation of in-game characters such as NPC, monsters, or players
 */
public abstract class Character extends Entity {

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
