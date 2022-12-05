package com.engynear.game;


import com.engynear.game.board.CellColor;
import com.engynear.game.board.Field;
import com.engynear.game.player.Computer;
import com.engynear.game.player.Human;
import com.engynear.game.player.Player;
import com.engynear.game.stage.GameSessionStage;
import com.engynear.game.stage.InitialSessionStage;
import com.engynear.game.stage.ResultsSessionStage;
import com.engynear.game.stage.SessionStage;
import com.engynear.game.stats.Leaderboard;
import com.engynear.io.InputHandler;
import com.engynear.io.Output;

public class GameSession {
    private final Leaderboard leaderboard;

    public enum GameType {
        HUMAN_VS_HUMAN,
        HUMAN_VS_COMPUTER,
        COMPUTER_VS_COMPUTER
    }

    public enum GameDifficulty {
        EASY,
        ADVANCED
    }

    private Player player1;
    private Player player2;
    private final Field field;
    private SessionStage currentStage;
    private final Game game;
    public GameSession(GameType gameType, Leaderboard leaderboard, Game game) {
        this.field = new Field();
        SessionStage sessionStage = new InitialSessionStage(this);
        sessionStage.onBegin();
        this.currentStage = sessionStage;
        this.leaderboard = leaderboard;
        this.game = game;

        switch (gameType) {
            case HUMAN_VS_HUMAN -> {
                this.player1 = new Human(CellColor.BLACK);
                this.player2 = new Human(CellColor.WHITE);
            }
            case HUMAN_VS_COMPUTER -> {
                this.player1 = new Human(CellColor.BLACK);
                this.player2 = new Computer(CellColor.WHITE);
            }
            case COMPUTER_VS_COMPUTER -> {
                this.player1 = new Computer(CellColor.BLACK);
                this.player2 = new Computer(CellColor.WHITE);
            }
        }
    }

    public GameSession(GameType gameType, GameDifficulty gameDifficulty, Leaderboard leaderboard, Game game) {
        this(gameType, leaderboard, game);
        if (player1 instanceof Computer) {
            ((Computer) player1).setDifficulty(gameDifficulty);
        }
        if (player2 instanceof Computer) {
            ((Computer) player2).setDifficulty(gameDifficulty);
        }
    }
    public void start() {
        setStage(new GameSessionStage(this, field));
        setStage(new ResultsSessionStage(this, field, leaderboard));
        setStage(new InitialSessionStage(this));
    }

    public void end() {
        Output.printInfo("Do you want to restart? (y/n)");
        boolean restart = InputHandler.getYNInput();
        if (restart) {
            game.restart();
        } else {
            game.end();
        }
    }

    public void setStage(SessionStage sessionStage) {
        currentStage.onEnd();
        currentStage = sessionStage;
        currentStage.onBegin();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
