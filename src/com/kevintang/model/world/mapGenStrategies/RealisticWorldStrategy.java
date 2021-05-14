package com.kevintang.model.world.mapGenStrategies;

import com.kevintang.model.world.Direction;
import com.kevintang.model.world.Map;
import com.kevintang.model.world.Tile;
import com.kevintang.model.world.World;
import com.kevintang.model.world.terrains.Mountain;
import com.kevintang.model.world.terrains.Plain;
import com.kevintang.model.world.terrains.ShallowWater;
import com.kevintang.model.world.terrains.DeepWater;

import java.util.ArrayList;
import java.util.Random;

/**
 * Plots the map according to realistic terrain
 */
public class RealisticWorldStrategy implements MapGenStrategy {

    private static final Random random = new Random();
    private Map map;

    @Override
    public void generateMap(World world) {
        this.map = world.getMap();
        floodWorld();
        plotContinents(10, Math.min(map.getWidth(), map.getHeight())*2);
        erodeCoastline(map.getWidth()*map.getHeight()/10);
        raiseMountains(8, 30);
        carveRivers(6,10,30);
        // world.debugPrintMap();
    }

    /**
     * Fill world with water tiles
     */
    private void floodWorld() {
        for (int y=0; y< map.getHeight(); y++) {
            for (int x=0; x<map.getWidth(); x++) {
                map.getBoard()[y][x].setTerrain(new DeepWater());
            }
        }
    }

    /**
     * Plot circular continents in the water
     * @param numOriginPoints Number of origin points from which continents will spawn
     * @param maxRadius Maximum radius that restricts the size of a continent
     */
    private void plotContinents(int numOriginPoints, int maxRadius) {

        // Plot origin points
        ArrayList<Circle> circles = new ArrayList<>();
        for (int i=0; i<numOriginPoints; i++) {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());
            Circle circle = new Circle(y, x, random.nextInt(maxRadius));
            circles.add(circle);
        }

        // Draw circles from origin points
        for (int y=0; y<map.getHeight(); y++) {
            for (int x=0; x<map.getWidth(); x++) {
                Tile tile = map.getBoard()[y][x];
                for (Circle circle : circles) {
                    if (isInCircle(circle, tile)) tile.setTerrain(new Plain());
                }
            }
        }
    }

    /* Utility Methods for plotContinents() */
    static class Circle {

        public int originX;
        public int originY;
        public int radius;

        public Circle(int originY, int originX, int radius) {
            this.originX = originX;
            this.originY = originY;
            this.radius = radius;
        }
    }

    private boolean isInCircle(Circle circle, Tile tile) {
        int dx = Math.abs(tile.getX() - circle.originX);
        int dy = Math.abs(tile.getY() - circle.originY);
        int radius = circle.radius;
        return dx*dx + dy*dy <= radius;
    }

    /**
     * Shape a natural looking coastline by using free-travelling probes to convert coastal line into water
     * @param probeCount How many erosion probes to spawn to shape the coastline
     */
    private void erodeCoastline(int probeCount) {
        int count = 0;
        while (count < probeCount) {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());
            Tile tile = map.getBoard()[y][x];
            if (tile.getTerrain() instanceof DeepWater) {
                new CoastProbe(tile).erode();
                count++;
            }
        }
    }

    /**
     * Probe to generate realistic coastal contours
     */
    class CoastProbe {

        public Tile lastTile;
        public Tile currentTile;

        public CoastProbe(Tile currentTile) {
            this.currentTile = currentTile;
        }

        // Goes 1 unit forwards in a random direction until probe hits land,
        // then convert the hit tile to water.
        public void erode() {
            // Generate random direction
            Direction direction = Direction.values()[random.nextInt(4)];
            int targetX = currentTile.getX();
            int targetY = currentTile.getY();
            switch (direction) {
                case NORTH -> targetY -= 1;
                case SOUTH -> targetY += 1;
                case EAST -> targetX += 1;
                case WEST -> targetX -= 1;
            }
            // Try to set target tile to water if possible, else move forward and try again
            try {
                Tile targetTile = map.getBoard()[targetY][targetX];
                if (!(targetTile.getTerrain() instanceof DeepWater)) {
                    targetTile.setTerrain(new DeepWater());
                } else {
                    lastTile = currentTile;
                    currentTile = targetTile;
                    this.erode();
                }
            } catch (IndexOutOfBoundsException e) {
                this.erode();
            }
        }
    }

    /**
     * Shape realistic mountain ranges on land by using branching probes
     * @param probeCount Number of mountains ranges to be formed
     * @param mountainSize How large each mountain range is
     */
    private void raiseMountains(int probeCount, int mountainSize) {
        // Generate origin point in non-water tile
        int count = 0;
        while (count < probeCount) {
            Tile origin = null;
            while (origin == null || origin.getTerrain() instanceof DeepWater) {
                int randomX = random.nextInt(map.getWidth());
                int randomY = random.nextInt(map.getHeight());
                origin = map.getBoard()[randomY][randomX];
            }
            new MountainProbe(origin, mountainSize).raise();
            count++;
        }
    }

    /**
     * Probe to generate realistic mountain ranges
     */
    class MountainProbe {

        public Tile lastTile;
        public Tile currentTile;
        int size;

        public MountainProbe(Tile currentTile, int size) {
            this.currentTile = currentTile;
            this.size = size;
        }

        public void raise() {
            // Generate random direction
            Direction direction = Direction.values()[random.nextInt(4)];
            int targetX = currentTile.getX();
            int targetY = currentTile.getY();
            switch (direction) {
                case NORTH -> targetY -= 1;
                case SOUTH -> targetY += 1;
                case EAST -> targetX += 1;
                case WEST -> targetX -= 1;
            }
            // Try to place Mountain in target tile if target tile is not Water
            // spawn new mountain probe with smaller size given chance
            try {
                Tile targetTile = map.getBoard()[targetY][targetX];
                if (!(targetTile.getTerrain() instanceof DeepWater)) {
                    targetTile.setTerrain(new Mountain());
                    size--;
                    if (size <= 1) return;
                    if (random.nextInt(10) > 7) {
                        int branchSize = random.nextInt(size/2);
                        new MountainProbe(targetTile, branchSize).raise();
                    }
                } else {
                    lastTile = currentTile;
                    currentTile = targetTile;
                }
                this.raise();
            } catch (IndexOutOfBoundsException e) {
                this.raise();
            }
        }
    }

    /**
     * Shape realistic waterways on land by using branching probes
     * @param probeCount Number of mountains ranges to be formed
     * @param minLength Minimum length of a river
     * @param maxLength Maximum length of a river
     */
    private void carveRivers(int probeCount, int minLength, int maxLength) {
        // Generate origin point in non-water tile
        int count = 0;
        while (count < probeCount) {
            Tile origin = null;
            while (origin == null || origin.getTerrain() instanceof DeepWater) {
                int randomX = random.nextInt(map.getWidth());
                int randomY = random.nextInt(map.getHeight());
                origin = map.getBoard()[randomY][randomX];
            }
            new RiverProbe(origin, random.nextInt(maxLength)+minLength).flood();
            count++;
        }
    }

    /**
     * Probe to generate realistic rivers and marshlands
     */
    class RiverProbe {
        public Tile lastTile;
        public Tile currentTile;
        Direction flow;
        int flowStrength;
        int length;

        public RiverProbe(Tile currentTile, int length) {
            this.currentTile = currentTile;
            this.length = length;
        }

        public void flood() {
            // Generate new random flow direction when previous flow strength is exhausted
            if (flowStrength <= 0) {
                flow = Direction.values()[random.nextInt(4)];
                flowStrength = 5;
            }
            int targetX = currentTile.getX();
            int targetY = currentTile.getY();
            switch (flow) {
                case NORTH -> targetY -= 1;
                case SOUTH -> targetY += 1;
                case EAST -> targetX += 1;
                case WEST -> targetX -= 1;
            }
            flowStrength--;
            // Try to set target tile to ShallowWater if possible, else move forward and try again
            try {
                Tile targetTile = map.getBoard()[targetY][targetX];
                if (targetTile.getTerrain() instanceof DeepWater) return;
                if (!(targetTile.getTerrain() instanceof ShallowWater)) {
                    targetTile.setTerrain(new ShallowWater());
                    length--;
                    if (length <= 1) return;
                } else {
                    lastTile = currentTile;
                    currentTile = targetTile;
                }
                this.flood();
            } catch (IndexOutOfBoundsException e) {
                this.flood();
            }
        }
    }
}
