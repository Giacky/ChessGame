package players.evaluationFunctions;

import board.BoardInfo;
import board.Piece;
import utility.PlayerColor;
import utility.Point;

import java.util.ArrayList;


public class PointValue extends EvaluationFunction {
    private int pawnValue;
    private int knightValue;
    private int bishopValue;
    private int rookValue;
    private int queenValue;

    private int kingValue = 10000;


    public PointValue() {
        assignValues(PointValuePreset.DEFAULT.getValues());
    }
    
    public PointValue(int pawnValue, int knightValue, int bishopValue, int rookValue, int queenValue) {
        this.pawnValue = pawnValue;
        this.knightValue = knightValue;
        this.bishopValue = bishopValue;
        this.rookValue = rookValue;
        this.queenValue = queenValue;
    }

    public PointValue(PointValuePreset preset) {
        assignValues(preset.getValues());
    }

    @Override
    public int evaluate(BoardInfo boardInfo, PlayerColor playerColor) {
        int value = 0;
        Piece[][] board = boardInfo.getBoard();
        ArrayList<Point> playerPosition;
        ArrayList<Point> opposingPosition;
        if (playerColor == PlayerColor.WHITE) {
            playerPosition = boardInfo.getWhitePieces();
            opposingPosition = boardInfo.getBlackPieces();
        } else {
            playerPosition = boardInfo.getBlackPieces();
            opposingPosition = boardInfo.getWhitePieces();
        }
        value += findPieceValue(board, playerPosition);
        value -= findPieceValue(board, opposingPosition);
        return value;
    }

    private int findPieceValue(Piece[][] board, ArrayList<Point> opposingPosition) {
        int value = 0;
        for (Point p : opposingPosition) {
            Piece piece = board[p.x][p.y];
            if (piece == Piece.PAWN_W || piece == Piece.PAWN_B) {
                value += pawnValue;
            } else if (piece == Piece.KNIGHT_W || piece == Piece.KNIGHT_B) {
                value += knightValue;
            } else if (piece == Piece.BISHOP_W || piece == Piece.BISHOP_B) {
                value += bishopValue;
            } else if (piece == Piece.ROOK_W || piece == Piece.ROOK_B) {
                value += rookValue;
            } else if (piece == Piece.QUEEN_W || piece == Piece.QUEEN_B) {
                value += queenValue;
            } else if (piece == Piece.KNIGHT_W || piece == Piece.KING_B) {
                value += knightValue;
            }
        }
        return value;
    }

    private void assignValues(int[] values) {
        this.pawnValue = values[0];
        this.knightValue = values[1];
        this.bishopValue = values[2];
        this.rookValue = values[3];
        this.queenValue = values[4];
    }

}
