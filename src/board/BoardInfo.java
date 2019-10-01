package board;

import game.GameState;
import utility.Move;
import utility.PlayerColor;
import utility.Point;

import java.util.LinkedList;

public class BoardInfo {
    private Piece[][] board;
    private PlayerColor playerColorTurn;
    private int turnCount;
    private LinkedList<Point> whitePieces;
    private LinkedList<Point> blackPieces;
    private Point king;
    private boolean inCheck;
    private boolean checkMate;
    private LinkedList<Move> possibleMoves;
    private GameState gameState = GameState.RUNNING;

    private BoardInfo(BoardInfo previousBoardInfo, Move move, boolean simulation) {
        this.checkMate = false;
        this.playerColorTurn = oppositePlayer(previousBoardInfo.getPlayerColorTurn());
        turnCount = previousBoardInfo.getTurnCount() + 1;
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();

        Piece[][] previousBoard = previousBoardInfo.getBoard();
        board = new Piece[8][];
        for(int i = 0; i < board.length; i++) {
            board[i] = previousBoard[i].clone();
        }
        if (move != null) {
            movePiece(move);
            if (move.isQueening()) {
                if (playerColorTurn == PlayerColor.WHITE) {
                    board[move.to.x][move.to.y] = Piece.QUEEN_W;
                } else {
                    board[move.to.x][move.to.y] = Piece.QUEEN_B;
                }
            }
        }

        settingUp(simulation);
    }

    public BoardInfo() {
        this.checkMate = false;
        board = new Piece[8][8];
        playerColorTurn = PlayerColor.WHITE;
        turnCount = 1;
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();

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

        settingUp(false);
    }

    private void settingUp(boolean simulation) {
        savePlayerPieces();

        king = findKing(playerColorTurn);

        this.inCheck = isInCheck(playerColorTurn);

        if (!simulation) {
            this.possibleMoves = allPossibleMoves(playerColorTurn);
            this.possibleMoves.removeIf(m -> simulateMove(m).isInCheck(playerColorTurn));
            updateGameState();
        }
    }

    private void savePlayerPieces() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y] != null) {
                    if (board[x][y].getPlayerColor() == PlayerColor.WHITE) {
                        Point piece = new Point(x, y);
                        whitePieces.add(piece);
                        if (board[x][y] == Piece.KING_W && playerColorTurn == PlayerColor.WHITE) {
                            king = piece;
                        }
                    } else {
                        Point piece = new Point(x, y);
                        blackPieces.add(piece);
                        if (board[x][y] == Piece.KING_B && playerColorTurn == PlayerColor.BLACK) {
                            king = piece;
                        }
                    }
                }
            }
        }
    }

    private Point findKing(PlayerColor playerColor) {
        LinkedList<Point> pieces;
        if (playerColor == PlayerColor.WHITE) {
            pieces = whitePieces;
        } else {
            pieces = blackPieces;
        }
        for (Point p : pieces) {
            if (board[p.x][p.y] == Piece.KING_W || board[p.x][p.y] == Piece.KING_B) {
                return p;
            }
        }
//        throw new RuntimeException("King not found");
        return null;
    }


    public LinkedList<Move> piecePossibleMoves(Point position) {
        LinkedList<Move> possibleMoves = new LinkedList<>();
        Piece piece = board[position.x][position.y];

        PlayerColor playerColor = piece.getPlayerColor();

        if (piece == Piece.QUEEN_W || piece == Piece.QUEEN_B) { //Queen
            possibleMoves.addAll(hvdMoves(position, true, true));
        }

        else if ((piece == Piece.KING_W || piece == Piece.KING_B)) { //King
            for (int dx=-1; dx<2; dx++) {
                for (int dy=-1; dy<2; dy++) {
                    if (dx!=0 || dy!=0) {
                        Point newPosition = new Point(position);
                        newPosition.moveBy(dx, dy);
                        if (checkBounds(newPosition) && (isEmpty(newPosition) || board[newPosition.x][newPosition.y].getPlayerColor() != playerColor)) {
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
                    addKnightMove(possibleMoves, position, newPosition);
                    newPosition = new Point(position);
                    newPosition.moveBy(j, i);
                    addKnightMove(possibleMoves, position, newPosition);
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
                        Move move = new Move(position, newPosition);
                        move.setQueening(queening(move));
                        possibleMoves.add(move);
                    }  else {
                        break;
                    }
                }
                //Diagonal capture
                for (int dx = -1; dx < 2; dx+=2) {
                    Point newPosition = new Point(position);
                    newPosition.moveBy(dx, 1);
                    if (checkBounds(newPosition) && !isEmpty(newPosition) && board[newPosition.x][newPosition.y].getPlayerColor() != playerColor) {
                        Move move = new Move(position, newPosition, true);
                        move.setQueening(queening(move));
                        possibleMoves.add(move);
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
                        Move move = new Move(position, newPosition);
                        move.setQueening(queening(move));
                        possibleMoves.add(move);
                    } else {
                        break;
                    }
                }
                //Diagonal capture
                for (int dx = -1; dx < 2; dx+=2) {
                    Point newPosition = new Point(position);
                    newPosition.moveBy(dx, -1);
                    if (checkBounds(newPosition) && !isEmpty(newPosition) && board[newPosition.x][newPosition.y].getPlayerColor() != playerColor) {
                        Move move = new Move(position, newPosition, true);
                        move.setQueening(queening(move));
                        possibleMoves.add(move);
                    }
                }
            }
        }
        return possibleMoves;
    }

    private boolean queening(Move move) {
        return (board[move.from.x][move.from.y].getPlayerColor() == PlayerColor.WHITE && move.to.y == 0) || (board[move.from.x][move.from.y].getPlayerColor() == PlayerColor.BLACK && move.to.y == 7);
    }

    private LinkedList<Move> allPossibleMoves(PlayerColor playerColor) {
        LinkedList<Move> possibleMoves = new LinkedList<>();
        LinkedList<Point> movablePieces;
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

    private LinkedList<Move> hvdMoves(Point position, boolean hvMoves, boolean dMoves) {
        LinkedList<Move> possibleMoves = new LinkedList<>();
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
                            } else if (board[newPosition.x][newPosition.y].getPlayerColor() != board[position.x][position.y].getPlayerColor()) {
                                possibleMoves.add(new Move(position, newPosition, true));
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

    private void addKnightMove(LinkedList<Move> possibleMoves, Point position, Point newPosition) {
        if (checkBounds(newPosition)) {
            if (isEmpty(newPosition)) {
                possibleMoves.add(new Move(position, newPosition));
            } else if (board[newPosition.x][newPosition.y].getPlayerColor() != playerColorTurn) {
                possibleMoves.add(new Move(position, newPosition, true));
            }
        }
    }

    public void movePiece(Move move) {
        board[move.to.x][move.to.y] = board[move.from.x][move.from.y];
        board[move.from.x][move.from.y] = null;
    }

    public BoardInfo performMove(Move move) {
        return new BoardInfo(this, move, false);
    }

    public BoardInfo simulateMove(Move move) {
        return new BoardInfo(this, move, true);
    }

    private boolean checkBounds(Point point) {
        return point.x >= 0 && point.y >= 0 && point.x < 8 && point.y < 8;
    }

    private boolean isEmpty(Point point) {
        return board[point.x][point.y] == null;
    }

    private boolean isInCheck(PlayerColor playerColor) {
        LinkedList<Move> opposingPossibleMoves = allPossibleMoves(oppositePlayer(playerColor));
        switch (playerColor) {
            case WHITE:
        }
        Point playerKing = findKing(playerColor);
        for (Move move : opposingPossibleMoves) {
            if (playerKing == null || playerKing.equals(move.to)) {
                return true;
            }
        }
        return false;
    }

    private void updateGameState() {
        if (possibleMoves.size() == 0) {
            switch (playerColorTurn) {
                case WHITE:
                    gameState =  GameState.WHITEWON;
                case BLACK:
                    gameState =  GameState.BLACKWON;
            }
        }
        gameState =  GameState.RUNNING;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Piece[][] getBoard() {
        return board;
    }

    public PlayerColor getPlayerColorTurn() {
        return playerColorTurn;
    }

    public int getTurnCount() {
        return turnCount;
    }


    public LinkedList<Point> getWhitePieces() {
        return whitePieces;
    }

    public LinkedList<Point> getBlackPieces() {
        return blackPieces;
    }

    public LinkedList<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public LinkedList<Move> getPiecePossibleMoves(Point piece) {
        LinkedList<Move> piecePossibleMoves = new LinkedList<>();
        for (Move move : possibleMoves) {
            if (piece.equals(move.from)) {
                piecePossibleMoves.add(move);
            }
        }
        return piecePossibleMoves;
    }


    public static PlayerColor oppositePlayer(PlayerColor playerColor) {
        switch (playerColor) {
            case WHITE:
                return PlayerColor.BLACK;
            default:
                return PlayerColor.WHITE;
        }
    }
}
