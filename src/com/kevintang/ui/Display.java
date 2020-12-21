package com.kevintang.ui;

public class Display {

    Pixel[][] screen;
    int width;
    int height;

    public Display(int width, int height) {
        this.screen = new Pixel[height][width];
        this.width = width;
        this.height = height;

        // Fill map with blank
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                screen[y][x] = new Pixel('.',y,x);
            }
        }
    }

    public void show(DisplayStrategy strategy) {
        if (strategy != null) screen = strategy.run(screen);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (Pixel[] pixels : this.screen) {
            for (Pixel pixel : pixels) {
                out.append(pixel.getSymbol());
                out.append(' ');
            }
            out.append('\n');
        }
        return out.toString();
    }

    public Pixel[][] getScreen() {
        return screen;
    }

    public void setScreen(Pixel[][] screen) {
        this.screen = screen;
    }
}
