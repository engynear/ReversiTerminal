package com.engynear.menu;

import com.engynear.game.Game;
import com.engynear.game.GameSession;
import com.engynear.io.InputHandler;

public class Menu {
    private final Game game;

    public Menu(Game game) {
        this.game = game;
    }

    public void display() {
        System.out.println("Please select an option:");
        for (MenuItems menuItem : MenuItems.values()) {
            System.out.println(menuItem.ordinal() + 1 + ". " + menuItem.getLabel());
        }
        int input = InputHandler.getMenuInput(MenuItems.values().length);
        switch (input) {
            case 1 -> displayNewGameMenu();
            case 2 -> {
                displayLeaderboard();
                display();
            }
            case 3 -> {
                System.out.println("Are you sure you want to exit? Y/N");
                if (InputHandler.getYNInput()) {
                    System.exit(0);
                } else {
                    display();
                }
                System.exit(0);
            }
        }
    }

    public void displayNewGameMenu() {
        System.out.println("Please select an option:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs Computer");
        int input = InputHandler.getMenuInput(2);
        GameSession gameSession;
        switch (input) {
            case 1 -> gameSession = new GameSession(GameSession.GameType.HUMAN_VS_HUMAN, game.getLeaderboard(), game);
            case 2 -> {
                System.out.println("Please select an option:");
                System.out.println("1. Easy");
                System.out.println("2. Advanced");
                int difficulty = InputHandler.getMenuInput(2);
                gameSession = switch (difficulty) {
                    case 1 ->
                            new GameSession(GameSession.GameType.HUMAN_VS_COMPUTER, GameSession.GameDifficulty.EASY, game.getLeaderboard(), game);
                    case 2 ->
                            new GameSession(GameSession.GameType.HUMAN_VS_COMPUTER, GameSession.GameDifficulty.ADVANCED, game.getLeaderboard(), game);
                    default -> throw new IllegalStateException("Unexpected value: " + input);
                };
            }
            default -> throw new IllegalStateException("Unexpected value: " + input);
        }
        game.start(gameSession);
    }

    public void displayLeaderboard() {
        game.getLeaderboard().print();
    }
}
