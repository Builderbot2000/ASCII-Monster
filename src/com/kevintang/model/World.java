package com.kevintang.model;

public class World {

    String name;
    Map map;

    public World(String name) {
        this.name = name;
    }

    public void generateMap() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
