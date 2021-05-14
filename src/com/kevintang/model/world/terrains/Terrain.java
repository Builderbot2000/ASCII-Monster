package com.kevintang.model.world.terrains;

import com.kevintang.model.entities.Entity;

import java.io.Serializable;

/**
 * Model representation of the terrain on a tile
 */
public abstract class Terrain implements Serializable {

    /**
     * Returns the symbol that represents this terrain
     * @return The symbol that represents this terrain
     */
    public abstract char getSymbol();

    /**
     * Determines if the terrain can host an entity of a certain type
     * @param entity The entity to be determined
     * @return Whether the terrain can host the entity
     */
    public abstract boolean isPassable(Entity entity);

    /**
     * Determines the movement cost for entity to pass through this terrain
     * @param entity The entity to be determined
     * @return The movement cost for entity to pass through this terrain
     */
    public abstract int getMovementCost(Entity entity);
}
