package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

import java.io.Serializable;

public abstract class Terrain implements Serializable {
    public abstract char getSymbol();

    public abstract boolean isPassable(Entity entity);

    public abstract int getMovementCost(Entity entity);
}
