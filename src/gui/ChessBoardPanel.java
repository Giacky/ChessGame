package gui;

import board.BoardInfo;
import board.Piece;
import utility.Move;
import utility.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ChessBoardPanel extends JPanel implements MouseListener {
    private int squareSize;
    private BoardInfo boardInfo;
    private Piece[][] board;

    private JPanel[][] tiles;

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

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                JPanel tile = new JPanel();
                tile.setPreferredSize(tileSize);
                tile.setMinimumSize(tileSize);
                tile.setMaximumSize(tileSize);
                if ((x + y) % 2 == 0) {
                    tile.setBackground(new Color(247, 206, 161));
                } else {
                    tile.setBackground(new Color(137, 98,81));
                }

                if (board[y][x] != null) {
                    JLabel pieceIcon = new JLabel(board[y][x].getImageIcon());
//                    pieceIcon.setHorizontalAlignment(JLabel.CENTER);
//                    pieceIcon.setVerticalAlignment(JLabel.CENTER);
                    tile.add(pieceIcon, BorderLayout.CENTER);
                    System.out.println(tile.getHeight());
                }
                add(tile);
                tiles[x][y] = tile;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point tilePosition = new Point(e.getX()/squareSize, e.getY()/squareSize);
        System.out.println(boardInfo.getBoard()[tilePosition.x][tilePosition.y]);

        Piece pieceSelected = board[tilePosition.x][tilePosition.y];
        if (pieceSelected != null && pieceSelected.getPlayerColor() == boardInfo.getPlayerColorTurn()) {
            ArrayList<Move> possibleMoves = boardInfo.piecePossibleMoves(tilePosition);
//            for (Move move : possibleMoves) {
//                tiles[move.to.x][move.to.y].setBackground(Color.BLUE);
//            }

        }
//        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
