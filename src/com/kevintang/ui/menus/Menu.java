package com.kevintang.ui.menus;

import com.kevintang.Main;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Base class for menus
 */
public abstract class Menu {

    String title; // Title of the menu
    String description; // Description of the menu
    ArrayList<String> options; // Menu options

    public Menu(String title, String description) {
        this.title = title;
        this.description = description;
        options = new ArrayList<>();
    }

    /**
     * Produces the string representation of the menu display
     * @return The string representation of the menu display
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n< ").append(title).append(" >\n");
        stringBuilder.append(description).append('\n');
        for (String option : options) {
            stringBuilder.append("- ").append(option).append('\n');
        }
        return stringBuilder.toString();
    }

    /**
     * Refresh menu display on every iteration unless broken by entry of "b" or routine
     */
    public void run() {
        while (true) {
            Main.clearConsole();
            System.out.println(this);
            if (routine()) break;
            Scanner scanner = Main.getScanner();
            System.out.println("Enter:");
            String in = scanner.nextLine();
            if (in.equals("B") || in.equals("b")) break;
            else onSelectListener(in);
        }
    }

    /**
     * Displays content that does not fit into the standard format of menus
     * @return Whether to break the looping display of this menu
     */
    public abstract boolean routine();

    /**
     * Event trigger when menu option is selected
     * @param in User input
     */
    public abstract void onSelectListener(String in);

    /* Getters & Setters */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getOptions() {
        return options;
    }
}
