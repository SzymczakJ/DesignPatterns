package ComputationGraph;

import Visitor.Visitor;

public class ScalarNode extends Node {

    public ScalarNode(double value) {
        this.scalarValue = value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitScalarNode(this);
    }
}
