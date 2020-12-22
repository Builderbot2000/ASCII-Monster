package com.kevintang.ui.displayStrategies;

import com.kevintang.ui.Pixel;

public class PlaceholderStrategy implements DisplayStrategy {
    @Override
    public Pixel[][] run(Pixel[][] screen) {
        int height = screen.length;
        int width = screen[0].length;
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                screen[y][x] = new Pixel('o',y,x);
            }
        }
        return screen;
    }
}
