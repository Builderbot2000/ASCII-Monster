package com.kevintang.model.world;

import com.kevintang.model.entities.Entity;
import com.kevintang.model.entities.characters.Player;
import com.kevintang.model.world.entityGenStrategies.EntityGenStrategy;
import com.kevintang.model.world.mapGenStrategies.MapGenStrategy;

import java.io.Serializable;

/**
 * The game world
 */
public class World implements Serializable {

    private String name; // Name of the world
    private Map map; // Container for world's map
    private Player player; // The player character

    /**
     * World is initialized as completely empty and blank
     * @param name Name of the world
     */
    public World(String name) {
        this.name = name;
    }

    /**
     * Generate a new map for the world
     * @param height Height of map
     * @param width Width of map
     * @param mapStrategy The method that plots all terrains and features onto the map
     * @param entityStrategy The method that places all entities on the map
     */
    public void generateMap(int height, int width, MapGenStrategy mapStrategy, EntityGenStrategy entityStrategy) {
        map = new Map(height, width);
        // Feed world with blank map into strategy to generate terrain map
        mapStrategy.generateMap(this);
        // Feed world with terrain map into strategy to generate full map
        entityStrategy.generateEntities(this);
        debugPrintMap();
    }

    /**
     * Print the entire map in its current state
     */
    public void debugPrintMap() {
        StringBuilder res = new StringBuilder();
        for (int y=0; y<map.getHeight(); y++) {
            for (int x=0; x<map.getWidth(); x++) {
                res.append(" ").append(map.getBoard()[y][x].getSymbol());
            }
            res.append('\n');
        }
        System.out.println(res);
    }

    /* Entity Manipulation Methods */

    /**
     * Spawn an entity onto the location given by its internal x and y positions
     * @param entity The entity to be spawned
     * @return Whether the spawning was a success or failure
     */
    public boolean spawnEntity(Entity entity) {
        try {
            if (entity instanceof Player) player = (Player) entity;
            return placeEntity(entity, entity.getX(), entity.getY(), 0);
        } catch (NullPointerException e) {
            System.out.println("Missing information in entity parameter!");
            return false;
        }
    }

    /**
     * Place an entity onto target (x, y) position with
     * @param entity The entity to be placed
     * @param x Target x position
     * @param y Target y position
     * @param mode Whether to show message when placement fails, useful when doing initial entity generation
     * @return Whether placement succeeded or failed
     */
    public boolean placeEntity(Entity entity, int x, int y, int mode) {
        try {
            Tile tile = map.getBoard()[y][x];
            if (!tile.getTerrain().isPassable(entity)) {
                if (mode != 0) System.out.println("Cannot traverse terrain!");
                return false;
            }
            map.getBoard()[entity.getY()][entity.getX()].getEntities().remove(entity);
            tile.getEntities().add(entity);
            for (Entity resident : tile.getEntities()) {
                resident.encounter(entity);
            }
            entity.setX(x);
            entity.setY(y);
            return true;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Cannot relocate, out of bounds!");
            return false;
        }
    }

    /**
     * Move an entity towards a certain direction with set distance
     * @param entity The entity to be moved
     * @param direction The direction that the entity will go
     * @param distance The distance that the entity will traverse in one move
     * @return Whether move succeeded or failed
     */
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

    /* Getters & Setters */

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
