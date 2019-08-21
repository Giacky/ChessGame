package game;

public enum GameState {
    IDLE,
    RUNNING,
    WHITEWON,
    BLACKWON,
    DRAW;

    public String toString() {
        switch (this) {
            case IDLE:
                return "Idle";
            case RUNNING:
                return "Running";
            case WHITEWON:
                return "White won";
            case BLACKWON:
                return "Black won";
            default:
                return "Draw";
        }
    }
}
