package com.kevintang.model.world;

import com.kevintang.model.entities.Character;
import com.kevintang.model.entities.Entity;
import com.kevintang.model.entities.Player;
import com.kevintang.ui.displayStrategies.DisplayStrategy;
import com.kevintang.ui.Pixel;

import java.io.Serializable;

public class World implements DisplayStrategy, Serializable {

    private String name;
    private Map map;
    private Character player;

    public World(String name) {
        this.name = name;
    }

    public void generateMap(int height, int width) {
        map = new Map(height, width);
        // Dummy generation: fill map board with x
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                map.getBoard()[y][x].setSymbol('_');
            }
        }
    }

    /**
     * Transcribe a viewport of map from player position onto screen
     * */
    @Override
    public Pixel[][] generateDisplayStrategy(Pixel[][] screen) {
        if (player == null) throw new IllegalStateException("No player in world, cannot define viewport!");
        int screenHeight = screen.length;
        int screenWidth = screen[0].length;
        int playerX = player.getX();
        int playerY = player.getY();
        int midX = screenWidth / 2;
        int midY = screenHeight / 2;
        for (int y=0; y<screenHeight; y++) {
            for (int x=0; x<screenWidth; x++) {
                char symbol;
                int trueX = playerX - midX + x;
                int trueY = playerY - midY + y;
                try {
                    symbol = map.getBoard()[trueY][trueX].getSymbol();
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    symbol = ' ';
                }
                screen[y][x] = new Pixel(symbol, y, x);
            }
        }
        return screen;
    }

    public void placePlayer(Player player) {
        map.getBoard()[player.getY()][player.getX()].getEntities().add(player);
        this.player = player;
    }

    public void placeEntity(Entity entity) {
        map.getBoard()[entity.getY()][entity.getX()].getEntities().add(entity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public Map getMap() {
        return map;
    }
}
