package Visitor;

import ComputationGraph.*;

public interface Visitor {
    public void visitBinaryOperationNode(BinaryOperationNode node);
    public void visitUnaryOperationNode(UnaryOperationNode node);
    public void visitScalarNode(ScalarNode node);
    public void visitMatrixNode(MatrixNode node);
}
