package board;

import utility.PlayerColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public enum Piece {
    QUEEN_W(PlayerColor.WHITE, "Queen White", "queen_white.png"),
    BISHOP_W(PlayerColor.WHITE, "Bishop White", "bishop_white.png"),
    KING_W(PlayerColor.WHITE, "King White", "king_white.png"),
    KNIGHT_W(PlayerColor.WHITE, "Knight White", "knight_white.png"),
    PAWN_W(PlayerColor.WHITE, "Pawn White", "pawn_white.png"),
    ROOK_W(PlayerColor.WHITE, "Rook White", "rook_white.png"),
    QUEEN_B(PlayerColor.BLACK, "Queen Black", "queen_black.png"),
    BISHOP_B(PlayerColor.BLACK, "Bishop Black", "bishop_black.png"),
    KING_B(PlayerColor.BLACK, "King Black", "king_black.png"),
    KNIGHT_B(PlayerColor.BLACK, "Knight Black", "knight_black.png"),
    PAWN_B(PlayerColor.BLACK, "Pawn Black", "pawn_black.png"),
    ROOK_B(PlayerColor.BLACK, "Rook Black", "rook_black.png");

    private static final String path = "src/assets/";
    
    private PlayerColor playerColor;
    private String pieceName;
    private ImageIcon imageIcon;

    Piece(PlayerColor playerColor, String pieceName, String imageName) {
        this.playerColor = playerColor;
        this.pieceName = pieceName;
        this.imageIcon = new ImageIcon(path + imageName);
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
