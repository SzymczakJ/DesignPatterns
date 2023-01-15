package ComputationGraph;

import Visitor.Visitor;
import org.apache.commons.math3.linear.RealMatrix;

public class BinaryOperationNode extends Node {
    public Node inputNodeA;
    public Node inputNodeB;
    public OperationType operationType;

    public BinaryOperationNode(OperationType operationType, Node inputNodeA, Node inputNodeB) {
        this.operationType = operationType;
        this.inputNodeA = inputNodeA;
        this.inputNodeB = inputNodeB;
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visitBinaryOperationNode(this);
    }
}
