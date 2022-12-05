package com.engynear.menu;

public enum MenuItems {
    NEW_GAME("New Game"),
    LEADERBOARD("Leaderboard"),
    EXIT("Exit");

    private final String label;

    MenuItems(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
