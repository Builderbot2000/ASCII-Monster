package com.kevintang.ui.menus;

import com.kevintang.Main;
import com.kevintang.model.Game;

import java.io.File;

/**
 * The load world save menu
 */
public class LoadWorldMenu extends Menu {

    private final File[] listOfFiles;

    /**
     * Get all files inside world saves directory and list them as options
     */
    public LoadWorldMenu() {
        super("Load World", "load a world from saved worlds:");
        String saveDirectoryPath = Main.getSavesFilePath();
        File file = new File(saveDirectoryPath);
        listOfFiles = file.listFiles();
        options.add("[b] Back to main menu");
        options.add("Enter index to load saved instance:");
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                options.add("[" + i + "] " + listOfFiles[i].getName());
            }
        }
    }

    @Override
    public boolean routine() { return false; }

    /**
     * Loads the world save file with index corresponding to user input number
     * @param in User input
     */
    @Override
    public void onSelectListener(String in) {
        int index = Integer.parseInt(in);
        String fileName = listOfFiles[index].getName();
        String saveDirectoryPath = Main.getSavesFilePath();
        String loadPath = saveDirectoryPath + "/" + fileName + "/" + fileName;
        Game.getInstance().loadWorld(loadPath);
    }
}
