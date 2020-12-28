package com.kevintang.ui.menus;

import com.kevintang.Main;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {

    String title;
    String description;
    ArrayList<String> options;

    public Menu(String title, String description) {
        this.title = title;
        this.description = description;
        options = new ArrayList<>();
    }

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

    public void run() {
        while (true) {
            Main.clearConsole();
            System.out.println(this.toString());
            if (routine()) break;
            Scanner scanner = Main.getScanner();
            System.out.println("Enter:");
            String in = scanner.nextLine();
            if (in.equals("B") || in.equals("b")) break;
            else onSelectListener(in);
        }
    }

    public abstract boolean routine();

    public abstract void onSelectListener(String in);

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
