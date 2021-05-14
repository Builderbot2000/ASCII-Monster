package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

/**
 * Rivers and marshlands, somewhat difficult to traverse and can be navigated by boat
 */
public class ShallowWater extends Terrain{
    @Override
    public char getSymbol() {
        return '~';
    }

    @Override
    public boolean isPassable(Entity entity) {
        return true;
    }

    @Override
    public int getMovementCost(Entity entity) {
        return 3;
    }
}
