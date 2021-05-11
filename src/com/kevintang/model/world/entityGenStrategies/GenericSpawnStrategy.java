package com.kevintang.model.world.entityGenStrategies;

import com.kevintang.model.entities.locations.City;
import com.kevintang.model.entities.locations.Dungeon;
import com.kevintang.model.world.Tile;
import com.kevintang.model.world.World;
import com.kevintang.model.world.terrains.Mountain;
import com.kevintang.model.world.terrains.Plain;

import java.util.Random;

public class GenericSpawnStrategy implements EntityGenStrategy{

    private static final Random random = new Random();
    private World world;

    @Override
    public void generateEntities(World world) {
        this.world = world;
        spawnCities(10);
        spawnDungeons(10);
    }

    void spawnCities(int cityCount) {
        int count = 0;
        while (count < cityCount) {
            Tile target = null;
            while (target == null
                    || !(target.getTerrain() instanceof Plain)){
                int randomX = random.nextInt(world.getMap().getWidth());
                int randomY = random.nextInt(world.getMap().getHeight());
                target = world.getMap().getBoard()[randomY][randomX];
                if (target.getEntities().isEmpty() && target.getTerrain() instanceof Plain) {
                    world.getMap().getBoard()[randomY][randomX].getEntities().add(new City(randomX,randomY));
                    count++;
                    break;
                }
            }
        }
    }

    void spawnDungeons(int dungeonCount) {
        int count = 0;
        while (count < dungeonCount) {
            Tile target = null;
            while (target == null
                    || !(target.getTerrain() instanceof Mountain)){
                int randomX = random.nextInt(world.getMap().getWidth());
                int randomY = random.nextInt(world.getMap().getHeight());
                target = world.getMap().getBoard()[randomY][randomX];
                if (target.getEntities().isEmpty() && target.getTerrain() instanceof Mountain) {
                    world.getMap().getBoard()[randomY][randomX].getEntities().add(new Dungeon(randomX,randomY));
                    count++;
                    break;
                }
            }
        }
    }
}
