package com.kevintang.ui;

/**
 * Predefined and preserved settings of screen properties
 */
public class ScreenSettings {

    private static ScreenSettings instance;

    public static ScreenSettings getInstance() {
        if (instance == null) instance = new ScreenSettings();
        return instance;
    }

    private int screenHeight;
    private int screenWidth;
    private int HUDHeight;
    private int HUDWidth;

    public ScreenSettings() {
        // Inject default settings
        screenHeight = 21;
        screenWidth = 33;
        HUDHeight = 6;
        HUDWidth = 33;
    }

    // Getters & Setters

    public static void setInstance(ScreenSettings instance) {
        ScreenSettings.instance = instance;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getHUDHeight() {
        return HUDHeight;
    }

    public void setHUDHeight(int HUDHeight) {
        this.HUDHeight = HUDHeight;
    }

    public int getHUDWidth() {
        return HUDWidth;
    }

    public void setHUDWidth(int HUDWidth) {
        this.HUDWidth = HUDWidth;
    }
}
