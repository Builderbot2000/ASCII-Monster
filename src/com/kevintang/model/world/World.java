package com.kevintang.model.world;

import com.kevintang.model.entities.Entity;
import com.kevintang.model.entities.characters.Player;
import com.kevintang.model.world.entityGenStrategies.EntityGenStrategy;
import com.kevintang.model.world.mapGenStrategies.MapGenStrategy;
import com.kevintang.ui.Pixel;
import com.kevintang.ui.displayStrategies.DisplayStrategy;

import java.io.Serializable;

public class World implements DisplayStrategy, Serializable {

    private String name;
    private Map map;
    private Player player;

    public World(String name) {
        this.name = name;
    }

    public void generateMap(int height, int width, MapGenStrategy mapStrategy, EntityGenStrategy entityStrategy) {
        map = new Map(height, width);
        mapStrategy.generateMap(map);
        entityStrategy.generateEntities(this);
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
                    symbol = '?';
                }
                screen[y][x] = new Pixel(symbol, y, x);
            }
        }
        return screen;
    }

    public boolean spawnEntity(Entity entity) {
        try {
            if (entity instanceof Player) player = (Player) entity;
            return placeEntity(entity, entity.getX(), entity.getY(), 0);
        } catch (NullPointerException e) {
            System.out.println("Missing information in entity parameter!");
            return false;
        }
    }

    public boolean placeEntity(Entity entity, int targetX, int targetY, int mode) {
        try {
            Tile tile = map.getBoard()[targetY][targetX];
            if (!tile.getTerrain().isPassable(entity)) {
                if (mode != 0) System.out.println("Cannot traverse terrain!");
                return false;
            }
            map.getBoard()[entity.getY()][entity.getX()].getEntities().remove(entity);
            tile.getEntities().add(entity);
            for (Entity resident : tile.getEntities()) {
                resident.encounter(entity);
            }
            entity.setX(targetX);
            entity.setY(targetY);
            return true;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot relocate, out of bounds!");
            return false;
        }
    }

    public boolean moveEntity(Entity entity, Direction direction, int distance) {
        int targetX = entity.getX();
        int targetY = entity.getY();
        switch (direction) {
            case NORTH -> targetY -= distance;
            case SOUTH -> targetY += distance;
            case EAST -> targetX += distance;
            case WEST -> targetX -= distance;
        }
        return placeEntity(entity, targetX, targetY, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map getMap() {
        return map;
    }
}
