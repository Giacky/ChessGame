package players.evaluationFunctions;

import board.BoardInfo;
import board.Piece;
import utility.PlayerColor;
import utility.Point;

import java.util.LinkedList;


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
        LinkedList<Point> playerPositions;
        LinkedList<Point> opposingPositions;
        if (playerColor == PlayerColor.WHITE) {
            playerPositions = boardInfo.getWhitePieces();
            opposingPositions = boardInfo.getBlackPieces();
        } else {
            playerPositions = boardInfo.getBlackPieces();
            opposingPositions = boardInfo.getWhitePieces();
        }
        value += findPieceValue(board, playerPositions);
        value -= findPieceValue(board, opposingPositions);
//        if (boardInfo.getPlayerColorTurn() == playerColor) {
//            value += boardInfo.getPossibleMoves().size();
//        } else {
//            value -= boardInfo.getPossibleMoves().size();
//        }
        return value;
    }

    private int findPieceValue(Piece[][] board, LinkedList<Point> positions) {
        int value = 0;
        for (Point p : positions) {
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
            } else if (piece == Piece.KING_W || piece == Piece.KING_B) {
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
