package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

public class Mountain implements Terrain {

    @Override
    public char getSymbol() {
        return 'A';
    }

    @Override
    public boolean isPassable(Entity entity) {
        return false;
    }

    @Override
    public int getMovementCost(Entity entity) {
        return 5;
    }
}
