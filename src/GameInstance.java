import board.Board;

public class GameInstance {
    private Board board;

    public GameInstance() {
        this.board = new Board();
        board.initialPositions();
    }
}
