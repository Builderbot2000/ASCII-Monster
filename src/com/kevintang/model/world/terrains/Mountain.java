package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

/**
 * High mountains, difficult to traverse and often hides dungeons underneath
 */
public class Mountain extends Terrain {

    public char getSymbol() {
        return 'A';
    }

    public boolean isPassable(Entity entity) {
        return true;
    }

    public int getMovementCost(Entity entity) {
        return 5;
    }
}
