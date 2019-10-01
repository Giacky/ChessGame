package players.evaluationFunctions;

import board.BoardInfo;
import utility.PlayerColor;

public class Mobility extends EvaluationFunction {

    @Override
    public int evaluate(BoardInfo boardInfo, PlayerColor playerColor) {
        int value = boardInfo.getPossibleMoves().size();
        if (boardInfo.getPlayerColorTurn() != playerColor) {
            return -1*value;
        } else {
            return value;
        }
    }
}
