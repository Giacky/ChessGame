package board;

import utility.Player;

import java.awt.*;

public enum Piece {
    QUEEN_W(Player.WHITE),
    BISHOP_W(Player.WHITE),
    KING_W(Player.WHITE),
    KNIGHT_W(Player.WHITE),
    PAWN_W(Player.WHITE),
    ROOK_W(Player.WHITE),
    QUEEN_B(Player.BLACK),
    BISHOP_B(Player.BLACK),
    KING_B(Player.BLACK),
    KNIGHT_B(Player.BLACK),
    PAWN_B(Player.BLACK),
    ROOK_B(Player.BLACK);

    private Image texture;
    private Player player;

    Piece(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
