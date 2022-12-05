package com.engynear.game.stage;

import com.engynear.game.GameSession;
import com.engynear.game.board.Field;
import com.engynear.game.player.Player;
import com.engynear.io.Output;

public class GameSessionStage implements SessionStage {
    //player1, player2, field in constructor
    GameSession gameSession;
    Player player1;
    Player player2;
    Field field;
    public GameSessionStage(GameSession gameSession, Field field) {
        this.gameSession = gameSession;
        this.player1 = gameSession.getPlayer1();
        this.player2 = gameSession.getPlayer2();
        this.field = field;
    }


    public boolean isGameOver() {
        return !player1.canMove(field) && !player2.canMove(field);
    }
    public void onBegin() {
        Player currentPlayer = player1;
        while (!isGameOver()) {
            field.print(field.getAvailableMoves(currentPlayer.getColor()));
            if (currentPlayer.canMove(field)) {
                currentPlayer.makeMove(field);
            }
            currentPlayer = currentPlayer == player1 ? player2 : player1;
        }
    }

    public void onEnd() {
        Output.printMessage("Game over");
    }
}
