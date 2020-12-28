package com.kevintang.model.world;

public class Map {

    private Tile[][] board;
    private int height;
    private int width;

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
