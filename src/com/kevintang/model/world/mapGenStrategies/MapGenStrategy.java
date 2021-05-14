package com.kevintang.model.world.mapGenStrategies;

import com.kevintang.model.world.World;

/**
 * Method that plots terrain features on a blank map
 */
public interface MapGenStrategy {

    void generateMap(World world);
}
