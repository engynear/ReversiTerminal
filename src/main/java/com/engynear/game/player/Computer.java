package com.engynear.game.player;

import com.engynear.game.GameSession;
import com.engynear.game.board.Cell;
import com.engynear.game.board.CellColor;
import com.engynear.game.board.Field;

import java.util.List;

public class Computer extends Player {
    GameSession.GameDifficulty gameDifficulty;
    public Computer(CellColor color) {
        super(color);
        gameDifficulty = GameSession.GameDifficulty.EASY;
    }

    public void setDifficulty(GameSession.GameDifficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public void makeMove(Field field) {
        System.out.println("Computer's turn with color " + color);
        switch (gameDifficulty) {
            case EASY -> makeEasyMove(field);
            case ADVANCED -> makeAdvancedMove(field);
            default -> throw new IllegalStateException("Unexpected value: " + gameDifficulty);
        }
    }

    private void makeAdvancedMove(Field field) {
        Field copy = field.makeCopy();
        List<Cell> availableMoves = copy.getAvailableMoves(color);
        Cell bestMove = null;
        int bestScore = 0;
        for (Cell move : availableMoves) {
            copy.makeMove(move, color);
            int score = copy.getScore(color);
            List<Cell> otherMoves = copy.getAvailableMoves(color.getOpposite());
            for (Cell opponentMove : otherMoves) {
                Field otherCopy = copy.makeCopy();
                otherCopy.makeMove(opponentMove, color.getOpposite());
                int score2 = otherCopy.getScore(color);
                if (score2 >= score) {
                    score = score2;
                }
            }
            if (score >= bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        if (bestMove != null) {
            System.out.println("Computer's move to " + bestMove);
            field.makeMove(field.getCell(bestMove.getX(), bestMove.getY()), color);
        }
    }

    private void makeEasyMove(Field field) {
        Field copy = field.makeCopy();
        List<Cell> availableMoves = copy.getAvailableMoves(color);
        Cell bestMove = null;
        int bestScore = 0;
        for (Cell move : availableMoves) {
            copy.makeMove(move, color);
            int score = copy.getScore(color);
            if (score >= bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        if (bestMove != null) {
            System.out.println("Computer's move to " + bestMove);
            field.makeMove(field.getCell(bestMove.getX(), bestMove.getY()), color);
        }
    }
}
