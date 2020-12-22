package com.kevintang;

import com.kevintang.ui.Driver;
import com.kevintang.ui.menus.MainMenu;
import com.kevintang.ui.menus.Menu;

import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static Menu menu;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        clearConsole();
        Driver.getInstance().run(null, null);
        menu = new MainMenu();
        menu.run();

        scanner.close();
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void clearConsole() {

    }
}
