package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

import java.io.Serializable;

public interface Terrain extends Serializable {

    char getSymbol();

    boolean isPassable(Entity entity);

    int getMovementCost(Entity entity);
}
