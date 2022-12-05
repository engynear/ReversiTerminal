package com.engynear.game.stage;

import com.engynear.game.GameSession;

public class InitialSessionStage implements SessionStage {
    GameSession gameSession;
    public InitialSessionStage(GameSession gameSession) {
        this.gameSession = gameSession;
    }


    public void onBegin() {
    }

    public void onEnd() {
    }

}
