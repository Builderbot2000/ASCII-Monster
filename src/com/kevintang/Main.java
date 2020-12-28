package com.kevintang;

import com.kevintang.model.Game;
import com.kevintang.ui.Driver;
import com.kevintang.ui.displayStrategies.PlaceholderStrategy;
import com.kevintang.ui.menus.MainMenu;
import com.kevintang.ui.menus.Menu;

import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        clearConsole();
        Game game = Game.getInstance();
        Menu menu = new MainMenu();
        menu.run();

        scanner.close();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void clearConsole() { }
}
