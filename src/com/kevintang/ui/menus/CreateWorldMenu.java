package com.kevintang.ui.menus;

import com.kevintang.Main;
import com.kevintang.model.Game;
import com.kevintang.model.entities.Player;
import com.kevintang.model.world.World;

import java.util.Random;
import java.util.Scanner;

public class CreateWorldMenu extends Menu {

    public CreateWorldMenu() {
        super("Create New World", "Choose world size:");
        options.add("[b] Back to main menu");
    }

    @Override
    public boolean routine() {
        Scanner scanner = Main.getScanner();
        Game.getInstance().setWorld(createWorld(scanner));
        World world = Game.getInstance().getWorld();
        Game.getInstance().getWorld().setPlayer(createPlayer(scanner, world));
        return true;
    }

    private World createWorld(Scanner scanner) {
        System.out.println("Please enter name of the world.");
        String name = scanner.nextLine();
        System.out.println("Please enter world height.");
        int height = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter world width.");
        int width = Integer.parseInt(scanner.nextLine());

        World world = new World(name);
        world.generateMap(height, width);
        System.out.println("World created.");
        return world;
    }

    private Player createPlayer(Scanner scanner, World world) {
        System.out.println("\nPlease enter name of your character.");
        String name = scanner.nextLine();
        int xLimit = world.getMap().getWidth();
        int yLimit = world.getMap().getHeight();
        Random random = new Random();
        int x = random.nextInt(xLimit);
        int y = random.nextInt(yLimit);

        Player player = new Player(name, x, y);
        world.getMap().getBoard()[y][x].getEntities().add(player);
        return player;
    }

    @Override
    public void onSelectListener(String in) { }
}
