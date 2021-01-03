package com.kevintang.ui.menus;

import com.kevintang.Main;
import com.kevintang.model.Game;

import java.io.File;

public class GameMenu extends Menu {

    public GameMenu() {
        super("World: " + Game.getInstance().getWorld().getName(), "Choose your action.");
        options.add("[n] Move North");
        options.add("[s] Move South");
        options.add("[w] Move West");
        options.add("[e] Move East");
        options.add("[i] Interact");
        options.add("[r] Save World");
        options.add("[b] Exit world");
    }

    @Override
    public boolean routine() {
        return true;
    }

    @Override
    public void onSelectListener(String in) {
        switch (in) {
            case "n" -> {
                System.out.println("Go North!");
            }
            case "s" -> {
                System.out.println("Go South!");
            }
            case "w" -> {
                System.out.println("Go West!");
            }
            case "e" -> {
                System.out.println("Go East!");
            }
            case "i" -> {
                System.out.println("Interact!");
            }
            case "r" -> {
                Game.getInstance().saveWorld();
            }
        }
    }
}
