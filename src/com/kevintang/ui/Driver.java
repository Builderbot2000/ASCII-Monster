package com.kevintang.ui;

import com.kevintang.Main;
import com.kevintang.ui.displayStrategies.DisplayStrategy;

/**
 * ASCII graphics driver for the game
 */
public class Driver {

    private static Driver instance;

    public static Driver getInstance() {
        if (instance == null) instance = new Driver();
        return instance;
    }

    private Driver() { }

    /**
     * Generates the updated display of the current state of the game
     * @param boardStrategy The current state of the game world
     * @param HUDStrategy The current state of the game character
     */
    public void run(DisplayStrategy boardStrategy, DisplayStrategy HUDStrategy) {

        // Create display and load settings
        Display display;
        ScreenSettings screenSettings = ScreenSettings.getInstance();
        Main.clearConsole();

        // Generate display of Game Board
        if (boardStrategy != null) {
            // Create empty display
            display = new Display(screenSettings.getScreenHeight(), screenSettings.getScreenWidth());
            // Load game state into display
            display.show(boardStrategy);
            // Output display text onto console
            System.out.println(display.toString() + '\n');
        }

        // Generate display of HUD
        if (HUDStrategy != null) {
            // Create empty display
            display = new Display(screenSettings.getHUDHeight(), screenSettings.getHUDWidth());
            // Load game state into display
            display.show(HUDStrategy);
            // Output display text onto console
            System.out.println(display.toString() + '\n');
        }
    }
}
