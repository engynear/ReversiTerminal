package com.engynear.game.player;

import com.engynear.game.board.Cell;
import com.engynear.game.board.CellColor;
import com.engynear.game.board.Field;
import com.engynear.io.InputHandler;
import com.engynear.io.Output;

import java.util.List;

public class Human extends Player{
    public Human(CellColor color) {
        super(color);
        Output.printMessage("Enter player " + color.getSymbol() + " name: ");
        this.name = InputHandler.getNonEmptyString();
    }

    @Override
    public void makeMove(Field field) {
        Output.printMessage("Player " + name + ", enter the coordinates of the cell you want to move to: ");
        Output.printMessage("Available moves: ");
        int i = 1;
        List<Cell> moves = field.getAvailableMoves(color);
        for (Cell cell : moves) {
            Output.printMessage(i + ". " + cell.toString());
            i++;
        }
        Output.printMessage("-1. Undo previous move");
        int x = InputHandler.getMoveInput(moves.size());
        if (x == -1) {
            field.undoMove(color);
            field.print(field.getAvailableMoves(color));
            makeMove(field);
        } else {
            field.saveMove(color);
            field.makeMove(moves.get(x - 1), color);
        }
    }
}
