package com.engynear.game.player;

import com.engynear.game.board.CellColor;
import com.engynear.game.board.Field;

public abstract class Player {
    protected CellColor color;
    protected String name;

    public Player(CellColor color) {
        this.color = color;
    }

    public boolean canMove(Field field) {
        return field.canMove(color);
    }

    abstract public void makeMove(Field field);

    public CellColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
