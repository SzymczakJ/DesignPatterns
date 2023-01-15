package ComputationGraph;

import java.util.ArrayList;

public class ComputationGraph {
    public ArrayList<Node> vertices = new ArrayList<>();

    public Node addMatrix(double[][] matrix) {
        Node matrixNode = new MatrixNode(matrix);
        vertices.add(matrixNode);
        return matrixNode;
    }

    public Node addScalar(double scalar) {
        Node scalarNode = new ScalarNode(scalar);
        vertices.add(scalarNode);
        return scalarNode;
    }

    public Node addBinaryOperation(Node a, Node b, OperationType operationType) {
        Node operationNode = new BinaryOperationNode(operationType, a, b);
        a.nextNode = operationNode;
        b.nextNode = operationNode;
        vertices.add(operationNode);
        return operationNode;
    }

    public Node addUnaryOperation(Node a, OperationType operationType) {
        Node operationNode = new UnaryOperationNode(operationType, a);
        a.nextNode = operationNode;
        vertices.add(operationNode);
        return operationNode;
    }

}
