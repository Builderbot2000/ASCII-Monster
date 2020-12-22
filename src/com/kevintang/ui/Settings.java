package com.kevintang.ui;

public class Settings {

    private static Settings instance;

    public static Settings getInstance() {
        if (instance == null) instance = new Settings();
        return instance;
    }

    int screenHeight;
    int screenWidth;
    int HUDHeight;
    int HUDWidth;
    int menuHeight;
    int menuWidth;

    public Settings() {
        // Inject default settings
        screenHeight = 20;
        screenWidth = 20;
        HUDHeight = 3;
        HUDWidth = 20;
        menuHeight = 3;
        menuWidth = 20;
    }

    public static void setInstance(Settings instance) {
        Settings.instance = instance;
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

    public int getMenuHeight() {
        return menuHeight;
    }

    public void setMenuHeight(int menuHeight) {
        this.menuHeight = menuHeight;
    }

    public int getMenuWidth() {
        return menuWidth;
    }

    public void setMenuWidth(int menuWidth) {
        this.menuWidth = menuWidth;
    }
}
