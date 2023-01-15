package ComputationGraph;

import ComputationGraph.Node;
import Visitor.Visitor;
import org.apache.commons.math3.linear.RealMatrix;

public class UnaryOperationNode extends Node {
    public Node inputNode;
    public OperationType operationType;

    public UnaryOperationNode(OperationType operationType, Node inputNode) {
        this.operationType = operationType;
        this.inputNode = inputNode;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitUnaryOperationNode(this);
    }
}
