package board;

import utility.Move;
import utility.PlayerColor;
import utility.Point;

import java.util.ArrayList;
import java.util.HashSet;

public class BoardInfo {
    private Piece[][] board;
    private PlayerColor playerColorTurn;
    private int turnCount;
    private ArrayList<Point> whitePieces;
    private ArrayList<Point> blackPieces;

    private BoardInfo(BoardInfo previousBoardInfo, Move move) {
        this.playerColorTurn = oppositePlayer(previousBoardInfo.getPlayerColorTurn());
        turnCount = previousBoardInfo.getTurnCount() + 1;
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        Piece[][] previousBoard = previousBoardInfo.getBoard();
        board = new Piece[8][];
        for(int i = 0; i < board.length; i++) {
            board[i] = previousBoard[i].clone();
        }

        performMove(move);

        getPlayerPieces();
    }

    public BoardInfo() {
        board = new Piece[8][8];
        playerColorTurn = PlayerColor.WHITE;
        turnCount = 1;
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();

        //Black player
        board[0][0] = Piece.ROOK_B;
        board[1][0] = Piece.KNIGHT_B;
        board[2][0] = Piece.BISHOP_B;
        board[3][0] = Piece.QUEEN_B;
        board[4][0] = Piece.KING_B;
        board[5][0] = Piece.BISHOP_B;
        board[6][0] = Piece.KNIGHT_B;
        board[7][0] = Piece.ROOK_B;
        board[0][1] = Piece.PAWN_B;
        board[1][1] = Piece.PAWN_B;
        board[2][1] = Piece.PAWN_B;
        board[3][1] = Piece.PAWN_B;
        board[4][1] = Piece.PAWN_B;
        board[5][1] = Piece.PAWN_B;
        board[6][1] = Piece.PAWN_B;
        board[7][1] = Piece.PAWN_B;

        //White player
        board[0][7] = Piece.ROOK_W;
        board[1][7] = Piece.KNIGHT_W;
        board[2][7] = Piece.BISHOP_W;
        board[3][7] = Piece.QUEEN_W;
        board[4][7] = Piece.KING_W;
        board[5][7] = Piece.BISHOP_W;
        board[6][7] = Piece.KNIGHT_W;
        board[7][7] = Piece.ROOK_W;
        board[0][6] = Piece.PAWN_W;
        board[1][6] = Piece.PAWN_W;
        board[2][6] = Piece.PAWN_W;
        board[3][6] = Piece.PAWN_W;
        board[4][6] = Piece.PAWN_W;
        board[5][6] = Piece.PAWN_W;
        board[6][6] = Piece.PAWN_W;
        board[7][6] = Piece.PAWN_W;
        getPlayerPieces();
    }

    private void getPlayerPieces() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] != null) {
                    if (board[x][y].getPlayerColor() == PlayerColor.WHITE) {
                        whitePieces.add(new Point(x, y));
                    } else {
                        blackPieces.add(new Point(x, y));
                    }
                }
            }
        }
    }


    public HashSet<Move> piecePossibleMoves(Point position) {
        HashSet<Move> possibleMoves = new HashSet<>();
        Piece piece = board[position.x][position.y];

        if (piece == Piece.QUEEN_W || piece == Piece.QUEEN_B) { //Queen
            possibleMoves.addAll(hvdMoves(position, true, true));
        }

        else if ((piece == Piece.KING_W || piece == Piece.KING_B)) { //King
            for (int dx=-1; dx<2; dx++) {
                for (int dy=-1; dy<2; dy++) {
                    if (dx!=0 || dy!=0) {
                        Point newPosition = new Point(position);
                        newPosition.moveBy(dx, dy);
                        if (checkBounds(newPosition) && (isEmpty(newPosition) || board[newPosition.x][newPosition.y].getPlayerColor() != playerColorTurn)) {
                            possibleMoves.add(new Move(position, newPosition));
                        }
                    }
                }
            }
        }

        else if ((piece == Piece.KNIGHT_W || piece == Piece.KNIGHT_B)) { //Knight
            for (int i=-2; i<3; i+=4) {
                for (int j = -1; j < 2; j+=2) {
                    Point newPosition = new Point(position);
                    newPosition.moveBy(i, j);
                    System.out.println(newPosition.toString());
                    if (checkBounds(newPosition) && (isEmpty(newPosition) || board[newPosition.x][newPosition.y].getPlayerColor() != playerColorTurn)) {
                        possibleMoves.add(new Move(position, newPosition));
                    }
                    newPosition = new Point(position);
                    newPosition.moveBy(j, i);
                    if (checkBounds(newPosition) && (isEmpty(newPosition) || board[newPosition.x][newPosition.y].getPlayerColor() != playerColorTurn)) {
                        possibleMoves.add(new Move(position, newPosition));
                    }
                }
            }
        }

        else if ((piece == Piece.BISHOP_W || piece == Piece.BISHOP_B)) { //Bishop
            possibleMoves.addAll(hvdMoves(position, false, true));
        }

        else if ((piece == Piece.ROOK_W || piece == Piece.ROOK_B)) { //Rook
            possibleMoves.addAll(hvdMoves(position, true, false));
        }

        else if ((piece == Piece.PAWN_W || piece == Piece.PAWN_B)) { //Pawn
            if (playerColorTurn == PlayerColor.BLACK) {
                int dyMax = 2;
                if (position.y == 1) {
                    dyMax = 3;
                }
                for (int dy = 1; dy < dyMax; dy++) {
                    Point newPosition = new Point(position);
                    newPosition.moveBy(0, dy);
                    if (checkBounds(newPosition) && isEmpty(newPosition)) {
                        possibleMoves.add(new Move(position, newPosition));
                    }  else {
                        break;
                    }
                }
                //Diagonal capture
                for (int dx = -1; dx < 2; dx+=2) {
                    Point newPosition = new Point(position);
                    newPosition.moveBy(dx, 1);
                    if (checkBounds(newPosition) && !isEmpty(newPosition) && board[newPosition.x][newPosition.y].getPlayerColor() != playerColorTurn) {
                        possibleMoves.add(new Move(position, newPosition));
                    }
                }
            } else {
                int dyMax = 2;
                if (position.y == 6) {
                    dyMax = 3;
                }
                for (int dy = -1; dy > -dyMax; dy--) {
                    Point newPosition = new Point(position);
                    newPosition.moveBy(0, dy);
                    if (checkBounds(newPosition) && isEmpty(newPosition)) {
                        possibleMoves.add(new Move(position, newPosition));
                    } else {
                        break;
                    }
                }
                //Diagonal capture
                for (int dx = -1; dx < 2; dx+=2) {
                    Point newPosition = new Point(position);
                    newPosition.moveBy(dx, -1);
                    if (checkBounds(newPosition) && !isEmpty(newPosition) && board[newPosition.x][newPosition.y].getPlayerColor() != playerColorTurn) {
                        possibleMoves.add(new Move(position, newPosition));
                    }
                }
            }
        }
        return possibleMoves;
    }

    public HashSet<Move> allPossibleMoves(PlayerColor playerColor) {
        HashSet<Move> possibleMoves = new HashSet<>();
        ArrayList<Point> movablePieces;
        if (playerColor == PlayerColor.WHITE) {
            movablePieces = whitePieces;
        } else {
            movablePieces = blackPieces;
        }
        for (Point position : movablePieces) {
            possibleMoves.addAll(piecePossibleMoves(position));
        }
        return possibleMoves;
    }

    private HashSet<Move> hvdMoves(Point position, boolean hvMoves, boolean dMoves) {
        HashSet<Move> possibleMoves = new HashSet<>();
        for (int dx=-1; dx<2; dx++) {
            for (int dy=-1; dy<2; dy++) {
                if ( (hvMoves &&(dx == 0 || dy == 0) && !(dx == 0 && dy == 0)) || (dMoves && !(dx == 0 || dy == 0)) ) {
                    int m = 1;
                    while(true) {
                        Point newPosition = new Point(position);
                        newPosition.moveBy(dx * m, dy * m);
                        if (checkBounds(newPosition)) {
                            if (isEmpty(newPosition)) {
                                possibleMoves.add(new Move(position, newPosition));
                            } else if (board[newPosition.x][newPosition.y].getPlayerColor() != playerColorTurn) {
                                possibleMoves.add(new Move(position, newPosition));
                                break;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                        m++;
                    }
                }
            }
        }
        return possibleMoves;
    }

    public void performMove(Move move) {
        board[move.to.x][move.to.y] = board[move.from.x][move.from.y];
        board[move.from.x][move.from.y] = null;
    }

    public BoardInfo simulateMovedBoard(Move move) {
        return new BoardInfo(this, move);
    }


    
    private boolean checkBounds(Point point) {
        return point.x >= 0 && point.y >= 0 && point.x < 8 && point.y < 8;
    }

    private boolean isEmpty(Point point) {
        return board[point.x][point.y] == null;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public PlayerColor getPlayerColorTurn() {
        return playerColorTurn;
    }

    public void setPlayerColorTurn(PlayerColor playerColorTurn) {
        this.playerColorTurn = playerColorTurn;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public ArrayList<Point> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Point> getBlackPieces() {
        return blackPieces;
    }

    private static PlayerColor oppositePlayer(PlayerColor playerColor) {
        switch (playerColor) {
            case WHITE:
                return PlayerColor.BLACK;
            default:
                return PlayerColor.WHITE;
        }
    }
}
