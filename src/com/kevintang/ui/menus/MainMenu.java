package com.kevintang.ui.menus;

import java.util.ArrayList;

public class MainMenu extends Menu{

    public MainMenu() {

        super("Adventure", "Start your adventure here!");

        // Inflate options
        options.add("[t] Start");
        options.add("[n] Create New World");
        options.add("[l] Load World");
        options.add("[s] Settings");
        options.add("[B/b] Exit");
    }

    @Override
    public void listen(String in) {
        switch (in) {
            case "t" -> System.out.println("Start!");
            case "n" -> {
                CreateWorldMenu createWorldMenu = new CreateWorldMenu();
                createWorldMenu.run();
            }
            case "l" -> {
                LoadWorldMenu loadWorldMenu = new LoadWorldMenu();
                loadWorldMenu.run();
            }
            case "s" -> System.out.println("Settings!");
        }
    }
}
