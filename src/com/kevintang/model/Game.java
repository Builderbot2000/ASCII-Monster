package com.kevintang.model;

import com.kevintang.Main;
import com.kevintang.model.world.World;
import com.kevintang.ui.Driver;
import com.kevintang.ui.displayStrategies.PlaceholderStrategy;
import com.kevintang.ui.menus.GameMenu;
import com.kevintang.ui.menus.Menu;

import java.util.Scanner;

public class Game {

    private static Game instance;

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private World world;

    public Game() { }

    public void run() {
        Driver driver = Driver.getInstance();
        while (true) {
            driver.run(world,new PlaceholderStrategy());
            Menu gameMenu = new GameMenu();
            gameMenu.run();
            Scanner scanner = Main.getScanner();
            System.out.println("Enter:");
            String in = scanner.nextLine();
            if (in.equals("B") || in.equals("b")) break;
            else gameMenu.onSelectListener(in);
        }
    }

    public World getWorld() { return world; }

    public void setWorld(World world) {
        this.world = world;
    }
}
