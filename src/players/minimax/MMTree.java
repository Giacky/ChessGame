package players.minimax;

import board.BoardInfo;
import game.GameInstance;
import utility.Move;

import java.util.ArrayList;
import java.util.HashSet;

public class MMTree {
    private GameInstance gameInstance;
    private MMNode root;
    private int maxDepth;

    public MMTree(GameInstance gameInstance, int maxDepth) {
        this.gameInstance = gameInstance;
        this.root = new MMNode(null, gameInstance.getBoardInfo(), null);
        this.maxDepth = maxDepth;
        expand(root);
    }

    public void expand(MMNode mmNode) {
        if (mmNode.getDepth() <= maxDepth) {
            BoardInfo boardInfo = mmNode.getBoardInfo();
            HashSet<Move> possibleMoves = boardInfo.allPossibleMoves(boardInfo.getPlayerColorTurn());
            ArrayList<MMNode> children = new ArrayList<>();
            for (Move move : possibleMoves) {
                BoardInfo childBoard = boardInfo.simulateMove(move);
                MMNode child = new MMNode(mmNode, childBoard, move);
                children.add(child);
                expand(child);
            }
        }
    }


}
