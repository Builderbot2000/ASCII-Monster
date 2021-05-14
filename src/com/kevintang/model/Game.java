package com.kevintang.model;

import com.kevintang.Main;
import com.kevintang.model.world.World;
import com.kevintang.ui.Driver;
import com.kevintang.ui.displayStrategies.HUDStrategies.EmptyHUDStrategy;
import com.kevintang.ui.displayStrategies.boardStrategies.WorldStrategy;
import com.kevintang.ui.menus.GameMenu;
import com.kevintang.ui.menus.Menu;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Scanner;

/**
 * Base container for the entire game, handles refresh of screen and function calls on model
 */
public class Game {

    private static Game instance;

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private World world; // The current loaded world
    private World view; // The current level of the world that is in view

    public Game() { }

    /**
     * Initiates the game cycle
     */
    public void run() {
        // Create new ASCII graphics driver
        Driver driver = Driver.getInstance();

        // Game loop: generate the updated world and HUD display on each iteration
        while (true) {
            System.out.println(" ");
            // Calls driver to output updated world and HUD display
            driver.run(new WorldStrategy(view),new EmptyHUDStrategy());
            // Displays updated menu
            Menu gameMenu = new GameMenu();
            gameMenu.run();
            // Registers user input
            Scanner scanner = Main.getScanner();
            System.out.println("Enter:");
            String in = scanner.nextLine();
            if (in.equals("B") || in.equals("b")) break;
            else gameMenu.onSelectListener(in);
        }
    }

    /**
     * Writes world into save file
     * @param path The location of the save file
     */
    private void writeWorld(String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(world);
        } catch (IOException e) {
            System.out.println("Write failed.");
            e.printStackTrace();
        }
    }

    /**
     * Saves the current world into save file
     */
    public void saveWorld() {
        String saveDirectoryPath = Main.getSavesFilePath() + "/" + world.getName();
        File file = new File(saveDirectoryPath);
        String savePath = saveDirectoryPath + "/" + world.getName();
        if (file.mkdir()) {
            writeWorld(savePath);
            System.out.println("New world save created.");
        } else overWriteWorld(savePath);
    }

    /**
     * Loads world from save file
     * @param path The location of the save file
     */
    public void loadWorld(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            world = (World) objectInputStream.readObject();
            System.out.println("World: " + world.getName() + " loaded");
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found!");
        } catch (IOException e) {
            System.out.println("IO error!");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found!");
        }
    }

    /**
     * Overwrites world into save file after prompt
     * @param path The location of the save file
     */
    private void overWriteWorld(String path) {
        System.out.println("World already exists.");
        System.out.println("would you like to overwrite the previous save?");
        System.out.println("[Y|N]");
        String input = Main.getScanner().nextLine();
        if (input.equals("Y") || input.equals("y")) {
            writeWorld(path);
            System.out.println("World save overwritten.");
        } else if (input.equals("N") || input.equals("n")) {
            System.out.println("Save operation terminated.");
        } else {
            System.out.println("Invalid input.");
            overWriteWorld(path);
        }
    }

    /**
     * Delete world save file
     * @param path The location of the save file
     */
    public void deleteWorld(String path) {
        File file = new File(path);
        if (file.exists()) {
            System.out.println("Are you sure you want to delete this world?");
            System.out.println("[Y|N]");
            String input = Main.getScanner().nextLine();
            if (input.equals("Y") || input.equals("y")) {
                try {
                    FileUtils.deleteDirectory(file);
                    System.out.println("World: " + file.getName() + " deleted.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (input.equals("N") || input.equals("n")) {
                System.out.println("Delete operation terminated.");
            } else {
                System.out.println("Invalid input.");
                deleteWorld(path);
            }
        } else System.out.println("Save not found.");
    }

    /* Getters & Setters */

    public World getWorld() { return world; }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getView() { return view; }

    public void setView(World view) { this.view = view; }
}
