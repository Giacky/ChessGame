package game;

import board.BoardInfo;
import players.Human;
import players.Player;
import players.PlayerType;
import players.mcts.MCTS;
import players.minimax.MiniMax;
import utility.Move;
import utility.PlayerColor;

public class GameInstance {
    private BoardInfo boardInfo;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;

    public GameInstance(PlayerType whiteType, PlayerType blackType) {
        this.whitePlayer = assignPlayer(whiteType, PlayerColor.WHITE);
        this.blackPlayer = assignPlayer(blackType, PlayerColor.BLACK);
        this.boardInfo = new BoardInfo();
        this.whitePlayer.setPlayerColor(PlayerColor.WHITE);
        this.blackPlayer.setPlayerColor(PlayerColor.BLACK);
    }

    private Player assignPlayer(PlayerType playerType, PlayerColor playerColor) {
        switch (playerType) {
            case HUMAN:
               return new Human(playerColor);
            case MINIMAX:
               return new MiniMax(playerColor);
            case MCTS:
                return new MCTS(playerColor);
            default:
                return new Human(playerColor);
        }
    }






    public BoardInfo getBoardInfo() {
        return boardInfo;
    }

    public void performMove(Move move) {
        boardInfo = boardInfo.performMove(move);
        System.out.println("----new turn----");
    }

    public void setBoardInfo(BoardInfo boardInfo) {
        this.boardInfo = boardInfo;
    }
}
