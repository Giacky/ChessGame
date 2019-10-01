package game;

import board.BoardInfo;
import board.Piece;
import players.evaluationFunctions.Mobility;
import players.evaluationFunctions.PointValuePreset;
import utility.Point;
import view.ChessBoardPanel;
import players.Human;
import players.Player;
import players.PlayerType;
import players.evaluationFunctions.PointValue;
import players.mcts.MCTS;
import players.minimax.MiniMax;
import utility.Move;
import utility.PlayerColor;

import java.util.LinkedList;

public class GameInstance {
    private ChessBoardPanel chessBoardPanel;
    private BoardInfo boardInfo;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private Object userInput;

    public GameInstance(PlayerType whiteType, PlayerType blackType) {
        this.whitePlayer = assignPlayer(whiteType, PlayerColor.WHITE);
        this.blackPlayer = assignPlayer(blackType, PlayerColor.BLACK);
        this.boardInfo = new BoardInfo();
        this.whitePlayer.setPlayerColor(PlayerColor.WHITE);
        this.blackPlayer.setPlayerColor(PlayerColor.BLACK);
        this.currentPlayer = whitePlayer;
    }

    private Player assignPlayer(PlayerType playerType, PlayerColor playerColor) {
        switch (playerType) {
            case HUMAN:
               return new Human(playerColor);
            case MINIMAX:
               return new MiniMax(playerColor, new PointValue(PointValuePreset.KAUFMAN2012), 3);
//                return new MiniMax(playerColor, new Mobility(), 3);
            case MCTS:
                return new MCTS(playerColor);
            default:
                return new Human(playerColor);
        }
    }

    public void gameLoop() {
        while((boardInfo.getGameState() == GameState.RUNNING)) {
            System.out.println();
            System.out.println("----new turn----   n: " + boardInfo.getTurnCount());
            System.out.println("Current Player Color: " + currentPlayer.getPlayerColor().toString());
            System.out.println("AI player: " + currentPlayer.getPlayerType().toString());
            if (currentPlayer.getPlayerType() == PlayerType.HUMAN) {
                synchronized (userInput) {
                    try {
                        userInput.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Move move = currentPlayer.bestMove(boardInfo);
                performMove(move);
                System.out.println("Move: " + move.toString());
            }
            currentPlayer = oppositePlayer(currentPlayer);
        }
        System.out.println("Ended");
        throw new RuntimeException("Game ended " + boardInfo.getGameState().toString());
    }

    public void performMove(Move move) {
        boardInfo = boardInfo.performMove(move);
        chessBoardPanel.setMovedPiece(move);
        if (move.isQueening()) {
            if (currentPlayer.getPlayerColor() == PlayerColor.WHITE) {
                chessBoardPanel.changeIcon(move.to, Piece.QUEEN_W);
            } else {
                chessBoardPanel.changeIcon(move.to, Piece.QUEEN_B);
            }
        }
    }

    public BoardInfo getBoardInfo() {
        return boardInfo;
    }

    private Player oppositePlayer(Player player) {
        if (player == whitePlayer) {
            return blackPlayer;
        } else {
            return whitePlayer;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public LinkedList<Move> getPiecePossibleMoves(Point piece) {
        LinkedList<Move> piecePossibleMoves = new LinkedList<>();
        LinkedList<Move> allPossibleMoves = boardInfo.getPossibleMoves();
        for (Move move : allPossibleMoves) {
            if (move.from.equals(piece)) {
                piecePossibleMoves.add(move);
            }
        }
        return piecePossibleMoves;
    }

    public PlayerColor getPlayerColorTurn() {
        return boardInfo.getPlayerColorTurn();
    }

    public void setUserInput(Object userInput) {
        this.userInput = userInput;
    }

    public void setChessBoardPanel(ChessBoardPanel chessBoardPanel) {
        this.chessBoardPanel = chessBoardPanel;
    }
}
