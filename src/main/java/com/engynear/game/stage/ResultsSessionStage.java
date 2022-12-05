package com.engynear.game.stage;

import com.engynear.game.GameSession;
import com.engynear.game.board.Field;
import com.engynear.game.player.Human;
import com.engynear.game.player.Player;
import com.engynear.game.stats.Leaderboard;
import com.engynear.io.Output;

public class ResultsSessionStage implements SessionStage {
    GameSession gameSession;
    Field field;
    Player player1;
    Player player2;
    Leaderboard leaderboard;

    public ResultsSessionStage(GameSession gameSession, Field field, Leaderboard leaderboard) {
        this.gameSession = gameSession;
        this.field = field;
        this.player1 = gameSession.getPlayer1();
        this.player2 = gameSession.getPlayer2();
        this.leaderboard = leaderboard;
    }

    public void onBegin() {
        int score1 = field.getScore(player1.getColor());
        int score2 = field.getScore(player2.getColor());

        if(player1 instanceof Human){
            Output.printInfo(player1.getName() + ": " + score1);
        } else {
            Output.printInfo("Computer " + player1.getColor() + ": " + score1);
        }

        if(player2 instanceof Human){
            Output.printInfo(player2.getName() + ": " + score2);
        } else {
            Output.printInfo("Computer " + player2.getColor() + ": " + score2);
        }

        if (score1 > score2) {
            Output.printInfo("Winner: " + player1.getName());
        } else if (score2 > score1) {
            Output.printInfo("Winner: " + player2.getName());
        } else {
            Output.printInfo("Draw");
        }

        if(player1 instanceof com.engynear.game.player.Human) {
            leaderboard.addResult(player1.getName(), score1);
        }
        if(player2 instanceof com.engynear.game.player.Human) {
            leaderboard.addResult(player2.getName(), score2);
        }

        leaderboard.printBest();
    }

    public void onEnd() {
        gameSession.end();
    }
}
