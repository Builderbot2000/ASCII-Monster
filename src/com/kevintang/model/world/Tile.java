package com.kevintang.model.world;

import com.kevintang.model.entities.Entity;
import com.kevintang.model.world.terrains.Terrain;

import java.io.Serializable;
import java.util.ArrayList;

public class Tile implements Serializable {

    private char symbol;
    private int y;
    private int x;
    private final ArrayList<Entity> entities;
    private Terrain terrain;

    public Tile(char symbol, int y, int x) {
        this.symbol = symbol;
        this.y = y;
        this.x = x;
        this.entities = new ArrayList<>();
    }

    /**
     * Output symbol according to proper game rules
     * @return the symbol to be displayed
     */
    public char getSymbol() {
        if (entities.size() != 0) symbol = entities.get(0).getSymbol();
        else symbol = terrain.getSymbol();
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
