import board.BoardInfo;

public class GameInstance {
    private BoardInfo board;

    public GameInstance() {
        this.board = new BoardInfo();
        board.initialPositions();
    }
}
