package ComputationGraph;

import ComputationGraph.Node;
import Visitor.Visitor;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class MatrixNode extends Node {

    public MatrixNode(double[][] value) {
        this.matrixValue = MatrixUtils.createRealMatrix(value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMatrixNode(this);
    }
}
