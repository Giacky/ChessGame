package players.minimax;

import board.BoardInfo;
import game.GameInstance;
import players.Player;
import players.evaluationFunctions.EvaluationFunction;
import utility.Move;
import utility.PlayerColor;

import java.util.ArrayList;


public class MiniMax extends Player {
    private EvaluationFunction evaluationFunction;
    private int maxDepth;
    private boolean isMaximisingPlayer;

    public MiniMax(PlayerColor playerColor) {
        super(playerColor);
        switch (playerColor) {
            case WHITE:
                isMaximisingPlayer = true;
                break;
            case BLACK:
                isMaximisingPlayer = false;
                break;
        }
    }

    @Override
    public Move makeMove(BoardInfo boardInfo) {
        return minimax(new MMNode(boardInfo, null, null), isMaximisingPlayer, new MMNode(Integer.MIN_VALUE), new MMNode(Integer.MAX_VALUE)).getMove();
    }

    public MMNode minimax(MMNode node, boolean isMaximisingPlayer, MMNode alpha, MMNode beta) {
        if (node.isLeaf() || node.getDepth() == maxDepth) {
            node.setValue(evaluationFunction.evaluate(node.getBoardInfo(), playerColor));
            return node;
        }
        if (isMaximisingPlayer) {
            MMNode bestNode = new MMNode(Integer.MIN_VALUE);
            ArrayList<MMNode> children = new ArrayList<>();
            for (MMNode child : children) {
                MMNode tempNode = minimax(child, false, alpha, beta);
                bestNode = MMNode.max(bestNode, tempNode);
                alpha = MMNode.max(alpha, bestNode);
                if (beta.lessThan(alpha)) {
                    break;
                }
            }
            return bestNode;
        } else {
            MMNode bestNode = new MMNode(Integer.MAX_VALUE);
            ArrayList<MMNode> children = new ArrayList<>();
            for (MMNode child : children) {
                MMNode tempNode = minimax(child, true, alpha, beta);
                bestNode = MMNode.min(bestNode, tempNode);
                beta = MMNode.min(beta, bestNode);
                if (beta.lessThan(alpha)) {
                    break;
                }
            }
            return bestNode;
        }
    }

}
