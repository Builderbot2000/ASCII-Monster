package com.kevintang.ui.menus;

import com.kevintang.Main;
import com.kevintang.model.Game;

import java.io.File;

public class DeleteWorldMenu extends Menu {

    private final File[] listOfFiles;

    public DeleteWorldMenu() {
        super("Delete World", "remove a world from saved worlds:");
        String saveDirectoryPath = Main.getSavesFilePath();
        File file = new File(saveDirectoryPath);
        listOfFiles = file.listFiles();
        options.add("[b] Back to main menu");
        options.add("Enter index to delete saved instance:");
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                options.add("[" + i + "] " + listOfFiles[i].getName());
            }
        }
    }

    @Override
    public boolean routine() {
        return false;
    }

    @Override
    public void onSelectListener(String in) {
        try {
            int index = Integer.parseInt(in);
            String fileName = listOfFiles[index].getName();
            String saveDirectoryPath = Main.getSavesFilePath();
            String deletePath = saveDirectoryPath + "/" + fileName;
            Game.getInstance().deleteWorld(deletePath);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
