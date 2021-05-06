package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

public class Water extends Terrain {

    @Override
    public char getSymbol() {
        return ' ';
    }

    @Override
    public boolean isPassable(Entity entity) {
        return false;
    }

    @Override
    public int getMovementCost(Entity entity) {
        return 999;
    }
}
