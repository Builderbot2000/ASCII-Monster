package com.kevintang.model.entities.locations;

import com.kevintang.model.entities.Entity;

public class Dungeon extends Location{

    public static final char SYMBOL = 'D';

    public Dungeon(char symbol, String name, int x, int y) {
        super(SYMBOL, name, x, y);
    }

    @Override
    public void interaction(Entity entity) {

    }

    @Override
    public void randomGenerate() {

    }
}
