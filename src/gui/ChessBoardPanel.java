package gui;

import board.BoardInfo;
import board.Piece;
import utility.Move;
import utility.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

public class ChessBoardPanel extends JPanel implements MouseListener {
    private int squareSize;
    private BoardInfo boardInfo;
    private Piece[][] board;

    private JPanel[][] tiles;

    private HashSet<Move> possibleMoves;
    private Point clickedTile;

    public ChessBoardPanel(BoardInfo boardInfo, int squareSize) {
        this.boardInfo = boardInfo;
        this.board = boardInfo.getBoard();
        this.squareSize = squareSize;
        Dimension panelSize = getPreferredSize();
        panelSize.width = squareSize * 8;
        setPreferredSize(panelSize);
        setMinimumSize(panelSize);
        setMaximumSize(panelSize);
        addMouseListener(this);

        tiles = new JPanel[8][8];

        setLayout(new GridLayout(8, 8));

        Dimension tileSize = new Dimension(squareSize, squareSize);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                JPanel tile = new JPanel();
                tile.setPreferredSize(tileSize);
                tile.setMinimumSize(tileSize);
                tile.setMaximumSize(tileSize);
                add(tile);
                tiles[x][y] = tile;
                if(board[x][y] != null) {
                    JLabel pieceIcon = new JLabel(board[x][y].getImageIcon());
                    tiles[x][y].add(pieceIcon, BorderLayout.CENTER);
                }
            }
        }
//        JLabel pieceIcon = new JLabel(board[1][1].getImageIcon());
//        tiles[1][1].add(pieceIcon, BorderLayout.CENTER);
        possibleMoves = new HashSet<>();
    }



    private void paintBoardTile(int x, int y) {
        if ((x + y) % 2 == 0) {
            tiles[x][y].setBackground(new Color(247, 206, 161));
        } else {
            tiles[x][y].setBackground(new Color(137, 98,81));
        }
    }

//    private void paintPiece(int x, int y) {
//        JLabel pieceIcon = new JLabel(board[x][y].getImageIcon());
//        tiles[x][y].add(pieceIcon, BorderLayout.CENTER);

//        JLabel pieceIcon = new JLabel(board[1][1].getImageIcon());
//        tiles[1][1].add(pieceIcon, BorderLayout.CENTER);

//        System.out.println("painted");

//    }

    private void paintMoveTile(int x, int y) {
        if ((x + y) % 2 == 0) {
            tiles[x][y].setBackground(new Color(70, 150, 255));
        } else {
            tiles[x][y].setBackground(new Color(0, 100, 240));
        }

    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//    }

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
        if (board[clickedTile.x][clickedTile.y].getPlayerColor() == boardInfo.getPlayerColorTurn()) {
            tiles[clickedTile.x][clickedTile.y].setBackground(new Color(0, 240, 19));
        }

//        for (int x = 0; x < 8; x++) {
//            for (int y = 0; y < 8; y++) {
//                if (board[x][y] != null) {
//                    paintPiece(x, y);
//                }
//            }
//        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        clickedTile = new Point(e.getX()/squareSize, e.getY()/squareSize);
        System.out.println(boardInfo.getBoard()[clickedTile.x][clickedTile.y]);

        Piece pieceSelected = board[clickedTile.x][clickedTile.y];
        if (pieceSelected != null && pieceSelected.getPlayerColor() == boardInfo.getPlayerColorTurn()) {
            possibleMoves = boardInfo.piecePossibleMoves(clickedTile);
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
