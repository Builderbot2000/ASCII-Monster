package com.kevintang.model;

public class Game {

    private static Game instance;

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private World world;

    public Game() { }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
