package controller;

import board.BoardInfo;
import board.Piece;
import game.GameInstance;
import players.PlayerType;
import utility.Move;
import utility.Point;
import view.ChessBoardPanel;
import view.GameView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class GameController implements MouseListener {
    private GameView gameView;
    private ChessBoardPanel chessBoardPanel;
    private GameInstance gameInstance;
    private LinkedList<Move> possibleMoves = new LinkedList<>();
    private final Object userInput = new Object();

    private int tileSize;

    public GameController(GameView gameView, GameInstance gameInstance) {
        this.gameView = gameView;
        this.chessBoardPanel = gameView.getChessBoardPanel();
        this.gameInstance = gameInstance;
        this.gameInstance.setChessBoardPanel(chessBoardPanel);
        this.tileSize = gameView.getTileSize();
        this.gameInstance.setUserInput(userInput);
        this.chessBoardPanel.addMouseListener(this);
        this.chessBoardPanel.drawPieces(gameInstance.getBoardInfo().getBoard());
        this.chessBoardPanel.repaint();
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (gameInstance.getCurrentPlayer().getPlayerType() == PlayerType.HUMAN) {
            BoardInfo boardInfo = gameInstance.getBoardInfo();
            Point clickedTile = new Point(e.getX() / tileSize, e.getY() / tileSize);
            Piece pieceSelected = boardInfo.getBoard()[clickedTile.x][clickedTile.y];
            if (pieceSelected != null && pieceSelected.getPlayerColor() == gameInstance.getPlayerColorTurn()) {
                possibleMoves = gameInstance.getPiecePossibleMoves(clickedTile);
                System.out.println(possibleMoves.size());
                chessBoardPanel.setPossibleMoves(possibleMoves);
            } else {
                boolean movePerformed = false;
                for (Move move : possibleMoves) {
                    if (clickedTile.equals(move.to)) {
                        movePerformed = true;
                        gameInstance.performMove(move);
                        possibleMoves.clear();
                        synchronized (userInput) {
                            userInput.notify();
                        }
                        break;
                    }
                } if (!movePerformed) {
                    possibleMoves.clear();
                    chessBoardPanel.clickedEmpty();
                }
            }
        }
        chessBoardPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
