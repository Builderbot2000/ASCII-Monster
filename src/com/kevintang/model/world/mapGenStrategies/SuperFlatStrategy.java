package com.kevintang.model.world.mapGenStrategies;

import com.kevintang.model.world.Map;
import com.kevintang.model.world.World;
import com.kevintang.model.world.terrains.Plain;

public class SuperFlatStrategy implements MapGenStrategy {

    @Override
    public void generateMap(World world) {
        Map map = world.getMap();
        for (int y=0; y< map.getHeight(); y++) {
            for (int x=0; x<map.getWidth(); x++) {
                map.getBoard()[y][x].setTerrain(new Plain());
            }
        }
    }
}
