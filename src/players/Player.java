package players;

import utility.PlayerColor;

public abstract class Player {
    private PlayerColor playerColor;


    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }
}
