package com.kevintang;

import com.kevintang.model.Game;
import com.kevintang.ui.Driver;
import com.kevintang.ui.displayStrategies.PlaceholderStrategy;
import com.kevintang.ui.menus.MainMenu;
import com.kevintang.ui.menus.Menu;

import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    // Location of the world save files directory
    private static final String savesFilePath =
            System.getProperty("user.dir").replaceAll("\\\\", "/") + "/saves";


    public static void main(String[] args) {

        // Create input scanner instance
        scanner = new Scanner(System.in);

        clearConsole();
        // Create new game instance and start main menu
        Game game = Game.getInstance();
        Menu menu = new MainMenu();
        menu.run();

        scanner.close();
    }

    /**
     * Clears the console
     */
    public static void clearConsole() { }

    // Getters

    public static Scanner getScanner() {
        return scanner;
    }

    public static String getSavesFilePath() { return savesFilePath; }


}
