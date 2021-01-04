package com.kevintang.ui.menus;

import com.kevintang.model.Game;
import com.kevintang.model.world.World;

public class MainMenu extends Menu {

    public MainMenu() {
        super("Adventure", "Start your adventure here!");

        // Inflate options
        options.add("[t] Start");
        options.add("[n] Create New World");
        options.add("[l] Load World");
        options.add("[d] Delete World");
        options.add("[s] Settings");
        options.add("[b] Exit");
    }

    @Override
    public boolean routine() {
        World world = Game.getInstance().getWorld();
        String worldName;
        if (world == null) worldName = "N/A";
        else worldName = world.getName();
        System.out.println("Current loaded world: " + worldName);
        return false;
    }

    @Override
    public void onSelectListener(String in) {
        String input = in.toLowerCase();
        switch (input) {
            case "t" -> {
                if (Game.getInstance().getWorld() == null) System.out.println("No world loaded!");
                else Game.getInstance().run();
            }
            case "n" -> new CreateWorldMenu().run();
            case "l" -> new LoadWorldMenu().run();
            case "d" -> new DeleteWorldMenu().run();
            case "s" -> System.out.println("Settings!");
        }
    }
}
