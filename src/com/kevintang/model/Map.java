package com.kevintang.model;

public class Map {

    Tile[][] board;
    int height;
    int width;

    public Map(Tile[][] board, int height, int width) {
        this.board = board;
        this.height = height;
        this.width = width;
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
