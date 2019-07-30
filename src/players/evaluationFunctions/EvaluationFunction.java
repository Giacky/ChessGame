package players.evaluationFunctions;

import board.BoardInfo;
import utility.PlayerColor;

public abstract class EvaluationFunction {

    public abstract int evaluate(BoardInfo boardInfo, PlayerColor playerColor);

}
