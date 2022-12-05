package com.engynear.game;

import com.engynear.game.stats.Leaderboard;
import com.engynear.io.Output;
import com.engynear.menu.Menu;

public class Game {
    private final Menu menu = new Menu(this);
    private GameSession gameSession;
    private final Leaderboard leaderboard = new Leaderboard();
    public Game() {
        System.out.println("Welcome to REVERSI!");
        menu.display();
    }

    public void start(GameSession gameSession) {
        if (this.gameSession != null) {
            Output.printInfo("Game is already in progress");
            return;
        }

        if (gameSession == null) {
            Output.printError("Game session cannot be null");
            return;
        }

        this.gameSession = gameSession;
        this.gameSession.start();
    }
    public void restart() {
        this.gameSession = null;
        menu.display();
    }

    public void end(){
        this.gameSession = null;
        System.exit(0);
    }
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }
}
