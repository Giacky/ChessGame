package players;

public enum PlayerType {
    HUMAN,
    MINIMAX,
    MCTS;

    public String toString() {
        switch (this) {
            case HUMAN:
                return "Human";
            case MINIMAX:
                return "MiniMax";
            case MCTS:
                return "MCTS";
            default:
                return "Unknown";
        }
    }
}
