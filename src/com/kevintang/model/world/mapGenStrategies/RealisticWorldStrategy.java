package com.kevintang.model.world.mapGenStrategies;

import com.kevintang.model.world.Direction;
import com.kevintang.model.world.Map;
import com.kevintang.model.world.Tile;
import com.kevintang.model.world.terrains.Plain;
import com.kevintang.model.world.terrains.Water;

import java.util.ArrayList;
import java.util.Random;

public class RealisticWorldStrategy implements MapGenStrategy {

    private static final Random random = new Random();
    private Map map;

    @Override
    public void generateMap(Map map) {
        this.map = map;
        floodWorld();
        plotContinents(10, Math.min(map.getWidth(), map.getHeight())*2);
        erodeCoastline(map.getWidth()*map.getHeight()/10);
        raiseMountains();
        carveRivers();
        debugPrintMap();
    }

    /**
     * Fill world with water tiles
     */
    private void floodWorld() {
        for (int y=0; y< map.getHeight(); y++) {
            for (int x=0; x<map.getWidth(); x++) {
                map.getBoard()[y][x].setTerrain(new Water());
            }
        }
    }

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

    private void erodeCoastline(int probeCount) {
        int count = 0;
        while (count < probeCount) {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());
            Tile tile = map.getBoard()[y][x];
            if (tile.getTerrain() instanceof Water) {
                new Probe(tile).go();
                count++;
            }
        }
    }

    class Probe {

        public Tile lastTile;
        public Tile currentTile;

        public Probe(Tile currentTile) {
            this.currentTile = currentTile;
        }

        public void go() {
            Direction direction = Direction.values()[random.nextInt(4)];
            int targetX = currentTile.getX();
            int targetY = currentTile.getY();
            switch (direction) {
                case NORTH -> targetY -= 1;
                case SOUTH -> targetY += 1;
                case EAST -> targetX += 1;
                case WEST -> targetX -= 1;
            }
            try {
                Tile targetTile = map.getBoard()[targetY][targetX];
                if (!(targetTile.getTerrain() instanceof Water)) {
                    targetTile.setTerrain(new Water());
                } else {
                    lastTile = currentTile;
                    currentTile = targetTile;
                    this.go();
                }
            } catch (IndexOutOfBoundsException e) {
                this.go();
            }
        }
    }

    private void raiseMountains() {

    }

    private void carveRivers() {

    }

    private void debugPrintMap() {
        StringBuilder res = new StringBuilder();
        for (int y=0; y<map.getHeight(); y++) {
            for (int x=0; x<map.getWidth(); x++) {
                res.append(" ").append(map.getBoard()[y][x].getSymbol());
            }
            res.append('\n');
        }
        System.out.println(res);
    }
}
