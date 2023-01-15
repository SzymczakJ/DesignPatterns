import ComputationGraph.ComputationGraph;
import ComputationGraph.Node;
import ComputationGraph.OperationType;
import Visitor.ComputationGraphVisitor;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;


public class Main {
    public static void main(String[] args) {
        double[][] matrixA = {
                {3, 2, 1},
                {9, 11, 5},
                {6, 0, 13}
        };
        double[][] matrixB = {
                {7, 4, 17},
                {2, 5, 1},
                {9, 8, 9}
        };
        double[][] matrixC = {
                {3, 2, 8},
                {8, 1, 2},
                {4, 3, 1}
        };
        double[][] matrixD = {
                {1, 1, 4},
                {6, 3, 7},
                {3, 2, 5}
        };
        double[][] matrixE = {
                {4, 5, 1},
                {2, 1, 1},
                {7, 5, 7}
        };
        double scalar = 2;

        ComputationGraph graph = new ComputationGraph();

        Node a = graph.addMatrix(matrixA);
        Node b = graph.addMatrix(matrixB);
        Node c = graph.addMatrix(matrixC);
        Node d = graph.addMatrix(matrixD);
        Node e = graph.addMatrix(matrixE);
        Node s = graph.addScalar(scalar);

        Node aAddB = graph.addBinaryOperation(a, b, OperationType.ADD);
        Node cSubD = graph.addBinaryOperation(c, d, OperationType.SUBTRACT);
        Node eMulScalar = graph.addBinaryOperation(e, s, OperationType.MULTIPLY_SCALAR);

        Node abMulCd = graph.addBinaryOperation(aAddB, cSubD, OperationType.MULTIPLY_MATRIX);
        Node esInv = graph.addUnaryOperation(eMulScalar, OperationType.INVERSE);

        Node abcdAddesInv = graph.addBinaryOperation(abMulCd, esInv, OperationType.ADD);

        ComputationGraphVisitor visitor = new ComputationGraphVisitor();
        for (Node node: graph.vertices) {
            node.accept(visitor);
        }


    }
}