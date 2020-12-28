package com.kevintang.ui.menus;

public class LoadWorldMenu extends Menu {

    public LoadWorldMenu() {
        super("Load World", "load a world from saved worlds:");
        options.add("[b] Back to main menu");
    }

    @Override
    public boolean routine() { return false; }

    @Override
    public void onSelectListener(String in) {

    }
}
