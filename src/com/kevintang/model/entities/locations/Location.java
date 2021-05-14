package com.kevintang.model.entities.locations;

import com.kevintang.model.entities.Entity;

/**
 * A special location that hosts its own sub-map and environment
 */
public abstract class Location extends Entity {

    public Location(char symbol, String name, int x, int y) {
        super(symbol, name, x, y);
    }

    @Override
    public void encounter(Entity entity) { }

    @Override
    public abstract void interaction(Entity entity);

    public abstract void randomGenerate();
}
