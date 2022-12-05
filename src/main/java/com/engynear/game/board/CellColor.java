package com.engynear.game.board;

public enum CellColor {
    WHITE('W'),
    BLACK('B'),
    EMPTY('.');

    private final char symbol;
    CellColor(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }
    public CellColor getOpposite() {
        if (this == WHITE) {
            return BLACK;
        } else if (this == BLACK) {
            return WHITE;
        } else {
            return EMPTY;
        }
    }
}
