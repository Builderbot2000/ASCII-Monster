package com.kevintang.ui;

public class Driver {

    private static Driver instance;

    public static Driver getInstance() {
        if (instance == null) instance = new Driver();
        return instance;
    }

    private Driver() { }

    public void run(DisplayStrategy boardStrategy, DisplayStrategy HUDStrategy, DisplayStrategy MenuStrategy) {

        Display display;

        // Display Game Board
        display = new Display(20,20);
        if (boardStrategy == null) display.show(new PlaceholderStrategy());
        else display.show(boardStrategy);
        System.out.println(display.toString() + '\n');

        // Display HUD
        display = new Display(20,3);
        if (boardStrategy == null) display.show(new PlaceholderStrategy());
        else display.show(HUDStrategy);
        System.out.println(display.toString() + '\n');

        // Display Menu
        display = new Display(20,3);
        if (boardStrategy == null) display.show(new PlaceholderStrategy());
        else display.show(MenuStrategy);
        System.out.println(display.toString() + '\n');
    }
}
