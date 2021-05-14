package com.kevintang.model.world;

import com.kevintang.model.entities.Entity;
import com.kevintang.model.world.terrains.Terrain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model representation of a single pixel on the game map
 */
public class Tile implements Serializable {

    private char symbol; // What character the tile will be displayed as on the screen
    private int y; // y position
    private int x; // x position
    private final ArrayList<Entity> entities; // List of entities that this tile contains
    private Terrain terrain; // The terrain on this tile

    public Tile(char symbol, int y, int x) {
        this.symbol = symbol;
        this.y = y;
        this.x = x;
        this.entities = new ArrayList<>();
    }

    /**
     * Output symbol of the most prioritized entity if there is entity on tile,
     * or output symbol of the tile's terrain
     * @return the symbol to be displayed
     */
    public char getSymbol() {
        if (entities.size() != 0) symbol = entities.get(0).getSymbol();
        else symbol = terrain.getSymbol();
        return symbol;
    }

    /* Getters & Setters */

    public void setSymbol(char symbol) { this.symbol = symbol; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public ArrayList<Entity> getEntities() { return entities; }

    public Terrain getTerrain() { return terrain; }

    public void setTerrain(Terrain terrain) { this.terrain = terrain; }
}
