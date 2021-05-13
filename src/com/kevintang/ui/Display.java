package com.kevintang.ui;

import com.kevintang.ui.displayStrategies.DisplayStrategy;

public class Display {

    private Pixel[][] screen; // Data structure for the display of screen
    private int width;
    private int height;

    public Display(int height, int width) {
        this.screen = new Pixel[height][width];
        this.height = height;
        this.width = width;
    }

    /**
     * Display screen using the given strategy
     * @param strategy A method that draws the intended imagery onto screen
     */
    public void show(DisplayStrategy strategy) {
        if (strategy != null) screen = strategy.generateDisplayStrategy(screen);
    }

    /**
     * Creates the string representation of the screen
     * @return The string representation of the screen
     */
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

    // Getters & Setters

    public Pixel[][] getScreen() {
        return screen;
    }

    public void setScreen(Pixel[][] screen) {
        this.screen = screen;
    }
}
