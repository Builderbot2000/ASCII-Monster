package com.kevintang.model;

import com.kevintang.ui.displayStrategies.DisplayStrategy;
import com.kevintang.ui.Pixel;

public class World implements DisplayStrategy {

    String name;
    Map map;

    public World(String name) {
        this.name = name;
    }

    public void generateMap(int height, int width) {
        map = new Map(new Tile[height][width], height, width);
        // Fill map board
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                map.getBoard()[y][x].setSymbol('x');
            }
        }
    }

    @Override
    public Pixel[][] run(Pixel[][] screen) {
        // Transcribe map board onto display screen
        int height = screen.length;
        int width = screen[0].length;
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                screen[y][x].setSymbol(map.getBoard()[y][x].getSymbol());
            }
        }
        return screen;
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
