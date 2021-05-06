package com.kevintang.ui.displayStrategies;

import com.kevintang.ui.Pixel;

public class EmptyHUDStrategy implements DisplayStrategy {

    @Override
    public Pixel[][] generateDisplayStrategy(Pixel[][] screen) {

        int height = screen.length;
        int width = screen[0].length;

        for (int x=0; x<width; x++) {
            screen[0][x] = new Pixel('-',0, x);
        }

        for (int y=1; y<height-1; y++) {
            screen[y][0] = new Pixel('|',y,0);
            for (int x=1; x<width-1; x++) {
                screen[y][x] = new Pixel(' ',y,x);
            }
            screen[y][width - 1] = new Pixel('|',y,0);
        }

        for (int x=0; x<width; x++) {
            screen[height - 1][x] = new Pixel('-',height, x);
        }

        return screen;
    }
}
