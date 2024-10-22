package com.kevintang.model.entities;

import java.io.Serializable;

/**
 * The representation for interactive objects on the game map
 */
public abstract class Entity implements Serializable {

    private final char symbol;
    private String name;
    private int x;
    private int y;

    public Entity(char symbol, String name, int x, int y) {
        this.symbol = symbol;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Involuntary interaction with entity when overlap occurs
     * @param entity The foreign entity that overlapped with this entity after move
     */
    public abstract void encounter(Entity entity);

    /**
     * Voluntary interaction triggered by a foreign entity
     * @param entity The foreign entity that overlapped with this entity
     *               after move and chose to initiate interaction
     */
    public abstract void interaction(Entity entity);

    /* Getters & Setters */

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
