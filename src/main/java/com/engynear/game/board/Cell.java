package com.engynear.game.board;

public class Cell {
    private final int x;
    private final int y;
    private CellColor color = CellColor.EMPTY;

    public Cell(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Cell coordinates are out of field range");
        }
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellColor getColor() {
        return color;
    }
    public void setColor(CellColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.valueOf((char)('A' + y)) + (x + 1);
    }
}
