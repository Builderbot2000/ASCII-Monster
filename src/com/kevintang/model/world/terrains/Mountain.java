package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

public class Mountain extends Terrain {

    public char getSymbol() {
        return 'A';
    }

    public boolean isPassable(Entity entity) {
        return false;
    }

    public int getMovementCost(Entity entity) {
        return 5;
    }
}
