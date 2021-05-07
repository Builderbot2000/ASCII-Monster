package com.kevintang.ui.menus;

import com.kevintang.model.Game;
import com.kevintang.model.entities.characters.Player;
import com.kevintang.model.world.Direction;

public class GameMenu extends Menu {

    Game game = Game.getInstance();
    Player player = Game.getInstance().getWorld().getPlayer();

    public GameMenu() {
        super("World: " + Game.getInstance().getWorld().getName(), "Choose your action.");
        options.add("[n] Move North");
        options.add("[s] Move South");
        options.add("[w] Move West");
        options.add("[e] Move East");
        options.add("[i] Interact");
        options.add("[r] Save World");
        options.add("[b] Exit world");
    }

    @Override
    public boolean routine() {
        return true;
    }

    @Override
    public void onSelectListener(String in) {
        String input = in.toLowerCase();
        switch (input) {
            case "n" -> game.getWorld().moveEntity(player, Direction.NORTH, 1);
            case "s" -> game.getWorld().moveEntity(player, Direction.SOUTH, 1);
            case "w" -> game.getWorld().moveEntity(player, Direction.WEST, 1);
            case "e" -> game.getWorld().moveEntity(player, Direction.EAST, 1);
            case "i" -> System.out.println("Interact!");
            case "r" -> Game.getInstance().saveWorld();
        }
    }
}
