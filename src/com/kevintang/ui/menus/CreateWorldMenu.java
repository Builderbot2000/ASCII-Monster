package com.kevintang.ui.menus;

import com.kevintang.Main;
import com.kevintang.model.Game;
import com.kevintang.model.entities.characters.Player;
import com.kevintang.model.world.World;
import com.kevintang.model.world.entityGenStrategies.GenericSpawnStrategy;
import com.kevintang.model.world.mapGenStrategies.RealisticWorldStrategy;

import java.util.Random;
import java.util.Scanner;

/**
 * The create new world save menu
 */
public class CreateWorldMenu extends Menu {

    public CreateWorldMenu() {
        super("Create New World", "Choose world size:");
        options.add("[b] Back to main menu");
    }

    /**
     * Creates blank, un-generated world
     * @return Whether to break the looping display of this menu
     */
    @Override
    public boolean routine() {
        Scanner scanner = Main.getScanner();
        Game.getInstance().setWorld(createWorld(scanner));

        World world = Game.getInstance().getWorld();
        createPlayer(scanner, world);
        return true;
    }

    /**
     * Prompt to specify world properties and create world accordingly
     * @param scanner Scanner for user input
     * @return The newly created world
     */
    private World createWorld(Scanner scanner) {
        // Prompt for world properties
        System.out.println("Please enter name of the world.");
        String name = scanner.nextLine();
        System.out.println("Please enter world height.");
        int height = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter world width.");
        int width = Integer.parseInt(scanner.nextLine());

        // Generate and return new world object based on given properties
        World world = new World(name);
        world.generateMap(height, width, new RealisticWorldStrategy(), new GenericSpawnStrategy());
        System.out.println("World created.");
        return world;
    }

    /**
     * Prompt to specify player properties and create new player character accordingly
     * @param scanner Scanner for user input
     * @param world The world that the player character is going to be in
     */
    private void createPlayer(Scanner scanner, World world) {
        System.out.println("\nPlease enter name of your character.");
        String name = scanner.nextLine();
        int xLimit = world.getMap().getWidth();
        int yLimit = world.getMap().getHeight();
        Random random = new Random();

        // Try to place player in a random valid location
        int maxTries = 1000;
        int tries = 0;
        while (tries < maxTries) {
            int x = random.nextInt(xLimit);
            int y = random.nextInt(yLimit);
            Player player = new Player(name, x, y);
            if (world.spawnEntity(player)) return;
            tries++;
        }
        System.out.println("Player placement failed.");
    }

    @Override
    public void onSelectListener(String in) { }
}
