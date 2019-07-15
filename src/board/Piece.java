package board;

import utility.PlayerColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public enum Piece {
    QUEEN_W(PlayerColor.WHITE, "Queen White", new ImageIcon("src/assets/queen_white.png")),
    BISHOP_W(PlayerColor.WHITE, "Bishop White", new ImageIcon("src/assets/bishop_white.png")),
    KING_W(PlayerColor.WHITE, "King White", new ImageIcon("src/assets/king_white.png")),
    KNIGHT_W(PlayerColor.WHITE, "Knight White", new ImageIcon("src/assets/knight_white.png")),
    PAWN_W(PlayerColor.WHITE, "Pawn White", new ImageIcon("src/assets/pawn_white.png")),
    ROOK_W(PlayerColor.WHITE, "Rook White", new ImageIcon("src/assets/rook_white.png")),
    QUEEN_B(PlayerColor.BLACK, "Queen Black", new ImageIcon("src/assets/queen_black.png")),
    BISHOP_B(PlayerColor.BLACK, "Bishop Black", new ImageIcon("src/assets/bishop_black.png")),
    KING_B(PlayerColor.BLACK, "King Black", new ImageIcon("src/assets/king_black.png")),
    KNIGHT_B(PlayerColor.BLACK, "Knight Black", new ImageIcon("src/assets/knight_black.png")),
    PAWN_B(PlayerColor.BLACK, "Pawn Black", new ImageIcon("src/assets/pawn_black.png")),
    ROOK_B(PlayerColor.BLACK, "Rook Black", new ImageIcon("src/assets/rook_black.png"));

    private PlayerColor playerColor;
    private String pieceName;
    private ImageIcon imageIcon;

    Piece(PlayerColor playerColor, String pieceName, ImageIcon imageIcon) {
        this.playerColor = playerColor;
        this.pieceName = pieceName;
        this.imageIcon = imageIcon;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public String toString() {
        return pieceName;
    }
}
