package com.kevintang.model.world;

import java.io.Serializable;

/**
 * The map of the game world where tile data is stored
 */
public class Map implements Serializable {

    private Tile[][] board; // Data container for tiles
    private int height;
    private int width;

    /**
     * At start, populate the entire board with empty tiles
     * @param height Height of map
     * @param width Width of map
     */
    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        this.board = new Tile[height][width];
        for (int y=0; y<height; y++) {
            for (int x=0; x<width; x++) {
                this.board[y][x] = new Tile(' ', y, x);
            }
        }
    }

    /* Getters & Setters */

    public Tile[][] getBoard() {
        return board;
    }

    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
