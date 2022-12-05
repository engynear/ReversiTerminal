package com.engynear.game.board;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Cell[][] cells = new Cell[8][8];

    public Field() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }

        cells[3][3].setColor(CellColor.WHITE);
        cells[3][4].setColor(CellColor.BLACK);
        cells[4][3].setColor(CellColor.BLACK);
        cells[4][4].setColor(CellColor.WHITE);
    }
    private Field previousWField = null;
    private Field previousBField = null;

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public List<Cell> getAvailableMoves(CellColor color) {
        List<Cell> availableMoves = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (checkMove(getCell(i,j), color)) {
                    availableMoves.add(cells[i][j]);
                }
            }
        }
        return availableMoves;
    }

    public void makeMove(Cell cell, CellColor color){
        if (checkMove(cell, color)) {
            cell.setColor(color);
            flipCells(cell, color);
        }
    }

    private void flipCells(Cell cell, CellColor color) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                flipCellsInLine(cell, color, i, j);
            }
        }
    }

    private void flipCellsInLine(Cell cell, CellColor color, int i, int i1) {
        int x = cell.getX();
        int y = cell.getY();
        while (true) {
            x += i;
            y += i1;
            if (x < 0 || x > 7 || y < 0 || y > 7) {
                break;
            }
            if (getCell(x, y).getColor() == CellColor.EMPTY) {
                break;
            }
            if (getCell(x, y).getColor() == color) {
                while (true) {
                    x -= i;
                    y -= i1;
                    if (x == cell.getX() && y == cell.getY()) {
                        break;
                    }
                    getCell(x, y).setColor(color);
                }
                break;
            }
        }
    }

    public void print() {
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(cells[i][j].getColor().getSymbol() + " ");
            }
            System.out.println();
        }
    }
    public void print(List<Cell> availableMoves) {
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                if (availableMoves.contains(cells[i][j])) {
                    System.out.print(availableMoves.indexOf(cells[i][j]) + 1 + " ");
                } else {
                    System.out.print(cells[i][j].getColor().getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }


    public boolean canMove (CellColor color) {
        return !getAvailableMoves(color).isEmpty();
    }
    public boolean checkMove(Cell cell, CellColor color) {
        if (cell.getColor() != CellColor.EMPTY) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (checkLine(cell, color, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLine(Cell cell, CellColor color, int dx, int dy) {
        int i = cell.getX() + dx;
        int j = cell.getY() + dy;
        if (i < 0 || i > 7 || j < 0 || j > 7) {
            return false;
        }
        if (cells[i][j].getColor() == color) {
            return false;
        }
        if (cells[i][j].getColor() == CellColor.EMPTY) {
            return false;
        }
        while (true) {
            i += dx;
            j += dy;
            if (i < 0 || i > 7 || j < 0 || j > 7) {
                return false;
            }
            if (cells[i][j].getColor() == CellColor.EMPTY) {
                return false;
            }
            if (cells[i][j].getColor() == color) {
                return true;
            }
        }
    }

    public void undoMove(CellColor color) {
        if (color == CellColor.WHITE) {
            if (previousWField != null) {
                cells = previousWField.cells;
                previousWField = null;
            } else {
                System.out.println("No moves to undo");
            }
        } else {
            if (previousBField != null) {
                cells = previousBField.cells;
                previousBField = null;
            } else {
                System.out.println("No moves to undo");
            }
        }
    }

    public void saveMove(CellColor color) {
        if (color == CellColor.WHITE) {
            previousWField = new Field();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    previousWField.cells[i][j].setColor(cells[i][j].getColor());
                }
            }
        } else {
            previousBField = new Field();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    previousBField.cells[i][j].setColor(cells[i][j].getColor());
                }
            }
        }
    }

    public int getScore(CellColor color) {
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (cells[i][j].getColor() == color) {
                    score++;
                }
            }
        }
        return score;
    }

    public Field makeCopy() {
        Field field = new Field();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field.cells[i][j].setColor(cells[i][j].getColor());
            }
        }
        return field;
    }
}
