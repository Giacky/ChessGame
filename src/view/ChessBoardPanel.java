package view;

import board.Piece;
import utility.Move;
import utility.Point;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class ChessBoardPanel extends JPanel {

    private TilePanel[][] tiles;
    private LinkedList<Move> possibleMoves;

    public ChessBoardPanel(int squareSize) {
        Dimension panelSize = getPreferredSize();
        panelSize.width = squareSize * 8;
        setPreferredSize(panelSize);
        setMinimumSize(panelSize);
        setMaximumSize(panelSize);

        tiles = new TilePanel[8][8];

        setLayout(new GridLayout(8, 8));

        Dimension tileSize = new Dimension(squareSize, squareSize);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                TilePanel tile = new TilePanel(tileSize);
                add(tile);
                tiles[x][y] = tile;
                paintBoardTile(x, y);
            }
        }
        possibleMoves = new LinkedList<>();
    }



    private void paintBoardTile(int x, int y) {
        if ((x + y) % 2 == 0) {
            tiles[x][y].setBackground(new Color(247, 206, 161));
        } else {
            tiles[x][y].setBackground(new Color(137, 98,81));
        }
    }

    public void drawPieces(Piece[][] board) {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if(board[x][y] != null) {
                    tiles[x][y].addPieceIcon(board[x][y].getImageIcon());
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Painting...");
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    paintBoardTile(x, y);
                }
            }
            for (Move m : possibleMoves) {
                if ((m.to.x + m.to.y) % 2 == 0) {
                    tiles[m.to.x][m.to.y].setBackground(new Color(70, 150, 255));
                } else {
                    tiles[m.to.x][m.to.y].setBackground(new Color(0, 100, 240));
                }
            }
        if (!possibleMoves.isEmpty()) {
            tiles[possibleMoves.get(0).from.x][possibleMoves.get(0).from.y].setBackground(new Color(0, 240, 19));
        }
    }

    public void setPossibleMoves(LinkedList<Move> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public void setMovedPiece(Move move) {
        if (move == null) {
            System.out.println("move is null");
        }
        if (tiles == null) {
            System.out.println("tiles is null");
        }

        tiles[move.to.x][move.to.y].substitutePieceLabel(tiles[move.from.x][move.from.y]);
    }

    public void changeIcon(Point position, Piece piece) {
        tiles[position.x][position.y].addPieceIcon(piece.getImageIcon());
    }

    public void clickedEmpty() {
        possibleMoves.clear();
    }


}
