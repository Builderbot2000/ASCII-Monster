package com.kevintang.model;

import com.kevintang.Main;
import com.kevintang.model.world.World;
import com.kevintang.ui.Driver;
import com.kevintang.ui.displayStrategies.PlaceholderStrategy;
import com.kevintang.ui.menus.GameMenu;
import com.kevintang.ui.menus.Menu;
import org.apache.commons.io.FileUtils;

import java.io.*;
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

    private void writeWorld(String savePath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(savePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(world);
        } catch (IOException e) {
            System.out.println("Write failed.");
            e.printStackTrace();
        }
    }

    public void saveWorld() {
        String saveDirectoryPath = Main.getSavesFilePath() + "/" + world.getName();
        File file = new File(saveDirectoryPath);
        String savePath = saveDirectoryPath + "/" + world.getName();
        if (file.mkdir()) {
            writeWorld(savePath);
            System.out.println("New world save created.");
        } else overWriteWorld(savePath);
    }

    public void loadWorld(String loadPath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(loadPath);
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

    private void overWriteWorld(String savePath) {
        System.out.println("World already exists.");
        System.out.println("would you like to overwrite the previous save?");
        System.out.println("[Y|N]");
        String input = Main.getScanner().nextLine();
        if (input.equals("Y") || input.equals("y")) {
            writeWorld(savePath);
            System.out.println("World save overwritten.");
        } else if (input.equals("N") || input.equals("n")) {
            System.out.println("Save operation terminated.");
        } else {
            System.out.println("Invalid input.");
            overWriteWorld(savePath);
        }
    }

    public void deleteWorld(String deletePath) {
        File file = new File(deletePath);
        if (file.exists()) {
            System.out.println("Are you sure you want to delete this world?");
            System.out.println("[Y|N]");
            String input = Main.getScanner().nextLine();
            if (input.equals("Y") || input.equals("y")) {
                try {
                    FileUtils.deleteDirectory(file);
                    System.out.println("World: " + file.getName() + " deleted.");
                } catch (IOException e) {
                    System.out.println("ERROR: directory deletion failed.");
                }
            } else if (input.equals("N") || input.equals("n")) {
                System.out.println("Delete operation terminated.");
            } else {
                System.out.println("Invalid input.");
                deleteWorld(deletePath);
            }
        } else System.out.println("Save not found.");
    }

    public World getWorld() { return world; }

    public void setWorld(World world) {
        this.world = world;
    }
}
