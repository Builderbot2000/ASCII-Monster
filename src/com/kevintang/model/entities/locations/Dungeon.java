package com.kevintang.model.entities.locations;

import com.kevintang.model.entities.Entity;

public class Dungeon extends Location{

    public static final char SYMBOL = 'D';

    public Dungeon(int x, int y) {
        super(SYMBOL, "NULL", x, y);
    }

    @Override
    public void interaction(Entity entity) {

    }

    @Override
    public void randomGenerate() {

    }
}
