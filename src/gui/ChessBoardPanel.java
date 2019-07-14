package gui;

import board.BoardInfo;
import board.Piece;

import javax.swing.*;
import java.awt.*;

public class ChessBoardPanel extends JPanel {
    public ChessBoardPanel(BoardInfo boardInfo) {
        Dimension panelSize = getPreferredSize();
        panelSize.width = 800;
        setPreferredSize(panelSize);
        Piece[][] board = boardInfo.getBoard();
        int lol = 0;
//        JPanel[][] tiles = new JPanel[8][8];

        setLayout(new GridLayout(8, 8));

        Dimension tileSize = new Dimension(100,100);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                JPanel tile = new JPanel();
                tile.setPreferredSize(tileSize);
                if ((x + y) % 2 == 0) {
                    tile.setBackground(new Color(247, 206, 161));
                } else {
                    tile.setBackground(new Color(137, 98,81));
                }

                if (board[x][7-y] != null) {
                    JLabel pieceIcon = new JLabel(board[x][7-y].getImageIcon());
                    tile.add(pieceIcon);
                    System.out.println(lol++);
                }
                add(tile);
//                tiles[x][y] = tile;
            }
        }

    }
}
