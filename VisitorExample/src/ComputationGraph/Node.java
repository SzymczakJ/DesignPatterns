package ComputationGraph;

import Visitor.Visitor;
import org.apache.commons.math3.linear.RealMatrix;

public abstract class Node {
    protected Node nextNode = null;
    public RealMatrix matrixValue = null;
    public Double scalarValue = null;

    public abstract void accept(Visitor visitor);
}
