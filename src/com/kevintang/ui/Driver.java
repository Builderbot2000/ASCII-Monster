package com.kevintang.ui;

import com.kevintang.Main;
import com.kevintang.ui.displayStrategies.DisplayStrategy;

public class Driver {

    private static Driver instance;

    public static Driver getInstance() {
        if (instance == null) instance = new Driver();
        return instance;
    }

    private Driver() { }

    public void run(DisplayStrategy boardStrategy, DisplayStrategy HUDStrategy) {

        Display display;
        Settings settings = Settings.getInstance();
        Main.clearConsole();

        // Display Game Board
        if (boardStrategy != null) {
            display = new Display(settings.getScreenHeight(), settings.getScreenWidth());
            display.show(boardStrategy);
            System.out.println(display.toString() + '\n');
        }

        // Display HUD
        if (HUDStrategy != null) {
            display = new Display(settings.getHUDHeight(), settings.getHUDWidth());
            display.show(HUDStrategy);
            System.out.println(display.toString() + '\n');
        }
    }
}
