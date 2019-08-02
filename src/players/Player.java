package players;

import board.BoardInfo;
import game.GameInstance;
import utility.Move;
import utility.PlayerColor;

public abstract class Player {
    protected PlayerColor playerColor;


    public Player(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }



    public abstract Move makeMove(BoardInfo boardInfo);


    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }


}
