package players;

import board.BoardInfo;
import utility.Move;
import utility.PlayerColor;

public class Human extends Player{

    public Human(PlayerColor playerColor) {
        super(playerColor);
    }

    @Override
    public Move makeMove(BoardInfo boardInfo) {
        return null;
    }
}
