package players;

import board.BoardInfo;
import utility.Move;
import utility.PlayerColor;

public class Human extends Player{
    private Move move;

    public Human(PlayerColor playerColor) {
        super(playerColor);
        this.playerType = PlayerType.HUMAN;
    }

    @Override
    public Move bestMove(BoardInfo boardInfo) {
        return move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
