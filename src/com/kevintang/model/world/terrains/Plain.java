package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

/**
 * Flat plains, easy to traverse and prime location for cities
 */
public class Plain extends Terrain {

    @Override
    public char getSymbol() {
        return '_';
    }

    @Override
    public boolean isPassable(Entity entity) {
        return true;
    }

    @Override
    public int getMovementCost(Entity entity) {
        return 1;
    }
}
