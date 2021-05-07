package com.kevintang.model.world.entityGenStrategies;

import com.kevintang.model.entities.locations.City;
import com.kevintang.model.world.Map;
import com.kevintang.model.world.Tile;
import com.kevintang.model.world.World;
import com.kevintang.model.world.mapGenStrategies.RealisticWorldStrategy;
import com.kevintang.model.world.terrains.Plain;
import com.kevintang.model.world.terrains.Water;

import java.util.Random;

public class GenericSpawnStrategy implements EntityGenStrategy{

    private static final Random random = new Random();
    private World world;

    @Override
    public void generateEntities(World world) {
        this.world = world;
        spawnCities(10);
        spawnDungeons();
    }

    void spawnCities(int cityCount) {
        int count = 0;
        while (count < cityCount) {
            Tile target = null;
            while (target == null
                    || !(target.getTerrain() instanceof Plain)
                    || !target.getEntities().isEmpty()) {
                int randomX = random.nextInt(world.getMap().getWidth());
                int randomY = random.nextInt(world.getMap().getHeight());
                target = world.getMap().getBoard()[randomY][randomX];
                System.out.println("Searching...");
                if (target.getEntities().isEmpty()) {
                    world.getMap().getBoard()[randomY][randomX].getEntities().add(new City(randomX,randomY));
                    count++;
                    System.out.println("City plopped.");
                }
            }
        }
    }

    void spawnDungeons() {

    }
}
