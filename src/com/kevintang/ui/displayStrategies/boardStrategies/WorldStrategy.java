package com.kevintang.ui.displayStrategies.boardStrategies;

import com.kevintang.model.entities.characters.Player;
import com.kevintang.model.world.Map;
import com.kevintang.model.world.World;
import com.kevintang.ui.Pixel;
import com.kevintang.ui.displayStrategies.DisplayStrategy;

/**
 * Wrapper class for the display of current state of game world and board
 */
public class WorldStrategy implements DisplayStrategy {

    private final Player player;
    private final Map map;

    public WorldStrategy(World world) {
        this.player = world.getPlayer();
        this.map = world.getMap();
    }

    /**
     * Transcribe a viewport of map from player position onto screen
     * */
    @Override
    public Pixel[][] generateDisplayStrategy(Pixel[][] screen) {

        if (player == null) throw new IllegalStateException("No player in world, cannot define viewport!");

        // Obtain game state information
        int screenHeight = screen.length;
        int screenWidth = screen[0].length;
        int playerX = player.getX();
        int playerY = player.getY();

        // Calculate midpoint position
        int midX = screenWidth / 2;
        int midY = screenHeight / 2;

        // Cast viewport unto screen
        for (int y=0; y<screenHeight; y++) {
            for (int x=0; x<screenWidth; x++) {
                char symbol;
                // Fit viewport center onto player position
                int trueX = playerX - midX + x;
                int trueY = playerY - midY + y;
                try {
                    symbol = map.getBoard()[trueY][trueX].getSymbol();
                } catch (IndexOutOfBoundsException | NullPointerException e) {
                    symbol = '?';
                }
                screen[y][x] = new Pixel(symbol, y, x);
            }
        }

        return screen;
    }
}
