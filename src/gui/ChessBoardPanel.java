package gui;

import board.BoardInfo;
import board.Piece;
import game.GameInstance;
import utility.Move;
import utility.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class ChessBoardPanel extends JPanel implements MouseListener {
    private int squareSize;
    private GameInstance gameInstance;
    private BoardInfo boardInfo;
    private Piece[][] board;

    private TilePanel[][] tiles;

    private HashSet<Move> possibleMoves;
    private Point clickedTile;

    public ChessBoardPanel(GameInstance gameInstance, int squareSize) {
        this.gameInstance = gameInstance;
        getUpdatedBoard();
        this.squareSize = squareSize;
        Dimension panelSize = getPreferredSize();
        panelSize.width = squareSize * 8;
        setPreferredSize(panelSize);
        setMinimumSize(panelSize);
        setMaximumSize(panelSize);
        addMouseListener(this);

        tiles = new TilePanel[8][8];

        setLayout(new GridLayout(8, 8));

        Dimension tileSize = new Dimension(squareSize, squareSize);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                TilePanel tile = new TilePanel(tileSize);
                add(tile);
                tiles[x][y] = tile;
                if(board[x][y] != null) {
                    tiles[x][y].addPieceIcon(board[x][y].getImageIcon());
                }
            }
        }
        possibleMoves = new HashSet<>();
    }



    private void paintBoardTile(int x, int y) {
        if ((x + y) % 2 == 0) {
            tiles[x][y].setBackground(new Color(247, 206, 161));
        } else {
            tiles[x][y].setBackground(new Color(137, 98,81));
        }
    }

    private void paintMoveTile(int x, int y) {
        if ((x + y) % 2 == 0) {
            tiles[x][y].setBackground(new Color(70, 150, 255));
        } else {
            tiles[x][y].setBackground(new Color(0, 100, 240));
        }
    }


        @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("painting...");
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                paintBoardTile(x, y);
            }
        }
        for (Move move : possibleMoves) {
            paintMoveTile(move.to.x, move.to.y);
        }
        if (clickedTile != null && board[clickedTile.x][clickedTile.y] !=null && board[clickedTile.x][clickedTile.y].getPlayerColor() == boardInfo.getPlayerColorTurn()) {
            tiles[clickedTile.x][clickedTile.y].setBackground(new Color(0, 240, 19));
        }

    }

    private void clickedPiece() {
        possibleMoves = boardInfo.piecePossibleMoves(clickedTile);
    }

    private void clickedMove(Move move) {
        gameInstance.performMove(move);
        possibleMoves.clear();
        tiles[move.to.x][move.to.y].substitutePieceLabel(tiles[move.from.x][move.from.y]);
        getUpdatedBoard();
    }

    private void getUpdatedBoard() {
        this.boardInfo = gameInstance.getBoardInfo();
        this.board = boardInfo.getBoard();
    }

    private void clickedEmpty() {
        possibleMoves.clear();
    }

    private Move findMove(Point tilePos) {
        for (Move m : possibleMoves) {
            if (m.to.x == tilePos.x && m.to.y == tilePos.y) {
                return m;
            }
        }
        return null;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        clickedTile = new Point(e.getX()/squareSize, e.getY()/squareSize);
        System.out.println(boardInfo.getBoard()[clickedTile.x][clickedTile.y]);
        Piece pieceSelected = board[clickedTile.x][clickedTile.y];
        Move move = findMove(clickedTile);
        if (move != null) { //clicked move tile
            clickedMove(move);
        } else if (pieceSelected == null || pieceSelected.getPlayerColor() != boardInfo.getPlayerColorTurn()) { //clicked empty tile or opposing player piece
            clickedEmpty();
        } else {
            clickedPiece();
        }
        System.out.println("possible moves: " + possibleMoves.size());
        repaint();
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
