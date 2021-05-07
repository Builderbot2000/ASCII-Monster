package com.kevintang.model.entities.locations;

import com.kevintang.model.entities.Entity;

public class City extends Location{

    public static final char SYMBOL = 'C';
    public City(int x, int y) {
        super(SYMBOL, "NULL", x, y);
    }

    @Override
    public void interaction(Entity entity) {

    }

    @Override
    public void randomGenerate() {

    }
}
