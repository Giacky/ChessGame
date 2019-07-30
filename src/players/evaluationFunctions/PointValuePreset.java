package players.evaluationFunctions;

public enum PointValuePreset {
    DEFAULT(1, 3, 3, 5, 9),
    KAUFMAN2012(100, 350, 350, 525, 1000),
    TURING(100, 300, 350, 500 ,1000);


    private int[] values = new int[5];

    PointValuePreset(int pawnValue, int knightValue, int bishopValue, int rookValue, int queenValue) {
        values[0] = pawnValue;
        values[1] = pawnValue;
        values[2] = pawnValue;
        values[3] = pawnValue;
        values[4] = pawnValue;
    }

    public int[] getValues() {
        return values;
    }
}
