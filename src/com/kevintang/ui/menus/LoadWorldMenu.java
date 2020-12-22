package com.kevintang.ui.menus;

public class LoadWorldMenu extends Menu{

    public LoadWorldMenu() {
        super("Load World", "load a world from saved worlds:");
        options.add("[B/b] Back to main menu");
    }

    @Override
    public void listen(String in) {

    }
}
