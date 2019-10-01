package players.minimax;

import utility.Move;

public class MoveValue {
    public Move move;
    public int value;

    public MoveValue(Move move, int value) {
        this.move = move;
        this.value = value;
    }

    public static MoveValue max(MoveValue moveValue1, MoveValue moveValue2) {
        if (moveValue1.value >= moveValue2.value) {
            return moveValue1;
        } else {
            return moveValue2;
        }
    }

    public static MoveValue min(MoveValue moveValue1, MoveValue moveValue2) {
        if (moveValue1.value <= moveValue2.value) {
            return moveValue1;
        } else {
            return moveValue2;
        }
    }

    public boolean lessThan(MoveValue moveValue) {
        return value <= moveValue.value;
    }
}
