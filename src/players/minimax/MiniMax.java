package players.minimax;

import board.BoardInfo;
import game.GameInstance;
import players.Player;
import players.PlayerType;
import players.evaluationFunctions.EvaluationFunction;
import utility.Move;
import utility.PlayerColor;

import java.util.LinkedList;


public class MiniMax extends Player {
    private EvaluationFunction evaluationFunction;
    private int maxDepth;
    private boolean isMaximisingPlayer;

    public MiniMax(PlayerColor playerColor, EvaluationFunction evaluationFunction, int maxDepth) {
        super(playerColor);
        this.playerType = PlayerType.MINIMAX;
        switch (playerColor) {
            case WHITE:
                isMaximisingPlayer = true;
                break;
            case BLACK:
                isMaximisingPlayer = false;
                break;
        }
        this.evaluationFunction = evaluationFunction;
        this.maxDepth = maxDepth;
    }

    @Override
    public Move bestMove(BoardInfo boardInfo) {
        return minimax(new MMNode(boardInfo, null, null), isMaximisingPlayer, new MMNode(Integer.MIN_VALUE), new MMNode(Integer.MAX_VALUE)).getMove();
    }

    public MMNode minimax(MMNode node, boolean isMaximisingPlayer, MMNode alpha, MMNode beta) {
        System.out.println("called");
        if (node.isLeaf() || node.getDepth() >= maxDepth) {
            node.setValue(evaluationFunction.evaluate(node.getBoardInfo(), playerColor));
            return node;
        }
        System.out.println("node to expand " + node.getDepth());
        if (isMaximisingPlayer) {
            MMNode bestNode = new MMNode(Integer.MIN_VALUE);
            LinkedList<MMNode> children = node.getChildren();
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
            LinkedList<MMNode> children = node.getChildren();
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
