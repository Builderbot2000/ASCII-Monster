package com.kevintang.ui;

public class PlaceholderStrategy implements DisplayStrategy{
    @Override
    public Pixel[][] run(Pixel[][] map) {
        int height = map.length;
        int width = map[0].length;
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                map[y][x] = new Pixel('o',y,x);
            }
        }
        return map;
    }
}
