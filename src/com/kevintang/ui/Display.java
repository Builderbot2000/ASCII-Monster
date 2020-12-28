package com.kevintang.ui;

import com.kevintang.ui.displayStrategies.DisplayStrategy;

public class Display {

    private Pixel[][] screen;
    private int width;
    private int height;

    public Display(int height, int width) {
        this.screen = new Pixel[height][width];
        this.height = height;
        this.width = width;
    }

    public void show(DisplayStrategy strategy) {
        if (strategy != null) screen = strategy.generateDisplayStrategy(screen);
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
