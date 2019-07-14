package board;

import utility.PlayerColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public enum Piece {
//    QUEEN_W(PlayerColor.WHITE, new ImageIcon("assets/queen_white.png")),
//    BISHOP_W(PlayerColor.WHITE, new ImageIcon("assets/bishop_white.png")),
//    KING_W(PlayerColor.WHITE, new ImageIcon("assets/king_white.png")),
//    KNIGHT_W(PlayerColor.WHITE, new ImageIcon("assets/knight_white.png")),
//    PAWN_W(PlayerColor.WHITE, new ImageIcon("assets/pawn_white.png")),
//    ROOK_W(PlayerColor.WHITE, new ImageIcon("assets/rook_white.png")),
//    QUEEN_B(PlayerColor.BLACK, new ImageIcon("assets/queen_black.png")),
//    BISHOP_B(PlayerColor.BLACK, new ImageIcon("assets/bishop_black.png")),
//    KING_B(PlayerColor.BLACK, new ImageIcon("assets/king_black.png")),
//    KNIGHT_B(PlayerColor.BLACK, new ImageIcon("assets/knight_black.png")),
//    PAWN_B(PlayerColor.BLACK, new ImageIcon("assets/pawn_black.png")),
//    ROOK_B(PlayerColor.BLACK, new ImageIcon("assets/rook_black.png"));

    QUEEN_W(PlayerColor.WHITE, new ImageIcon("assets/queen_white.png")),
    BISHOP_W(PlayerColor.WHITE, new ImageIcon("assets/bishop_white.png")),
    KING_W(PlayerColor.WHITE, new ImageIcon("assets/king_white.png")),
    KNIGHT_W(PlayerColor.WHITE, new ImageIcon("assets/knight_white.png")),
    PAWN_W(PlayerColor.WHITE, new ImageIcon("assets/pawn_white.png")),
    ROOK_W(PlayerColor.WHITE, new ImageIcon("assets/rook_white.png")),
    QUEEN_B(PlayerColor.BLACK, new ImageIcon("assets/queen_black.png")),
    BISHOP_B(PlayerColor.BLACK, new ImageIcon("assets/bishop_black.png")),
    KING_B(PlayerColor.BLACK, new ImageIcon("assets/king_black.png")),
    KNIGHT_B(PlayerColor.BLACK, new ImageIcon("assets/knight_black.png")),
    PAWN_B(PlayerColor.BLACK, new ImageIcon("assets/pawn_black.png")),
    ROOK_B(PlayerColor.BLACK, new ImageIcon("assets/rook_black.png"));

    private PlayerColor playerColor;
    private ImageIcon imageIcon;

    Piece(PlayerColor playerColor, ImageIcon imageIcon) {
        this.playerColor = playerColor;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }
}
