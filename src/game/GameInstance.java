package game;

import board.BoardInfo;
import players.Player;
import utility.Move;
import utility.PlayerColor;

public class GameInstance {
    private BoardInfo boardInfo;
    private Player whitePlayer;
    private Player blackPlayer;

    public GameInstance(Player whitePlayer, Player blackPlayer) {
        this.boardInfo = new BoardInfo();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.whitePlayer.setPlayerColor(PlayerColor.WHITE);
        this.blackPlayer.setPlayerColor(PlayerColor.BLACK);
    }

    public BoardInfo getBoardInfo() {
        return boardInfo;
    }

    public void performMove(Move move) {
        boardInfo = boardInfo.simulateMove(move);
    }

    public void setBoardInfo(BoardInfo boardInfo) {
        this.boardInfo = boardInfo;
    }
}
