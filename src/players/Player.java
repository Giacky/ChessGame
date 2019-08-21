package players;

import board.BoardInfo;
import game.GameInstance;
import utility.Move;
import utility.PlayerColor;

public abstract class Player {
    protected PlayerColor playerColor;
    protected PlayerType playerType;


    public Player(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }



    public abstract Move bestMove(BoardInfo boardInfo);


    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }
}
