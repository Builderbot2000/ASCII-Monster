package com.kevintang.ui.menus;

import com.kevintang.model.Game;

public class GameMenu extends Menu {

    public GameMenu() {
        super("World: " + Game.getInstance().getWorld().getName(), "Choose your action.");
        options.add("[n] Move North");
        options.add("[s] Move South");
        options.add("[w] Move West");
        options.add("[e] Move East");
        options.add("[i] Interact");
        options.add("[b] Exit world");
    }

    @Override
    public boolean routine() {
        return true;
    }

    @Override
    public void onSelectListener(String in) {

    }
}
