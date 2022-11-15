package decorator.decorators;

import decorator.ShapeCreator;
import javafx.scene.shape.Shape;

public class DottedDecorator extends ShapeCreatorDecorator {

    public DottedDecorator(ShapeCreator shapeCreator) {
        super(shapeCreator);
    }

    @Override
    public Shape createShape() {
        Shape shape = wrappedShapeCreator.createShape();
        shape.getStrokeDashArray().addAll(8.0, 7.0, 6.0, 5.0, 4.0);
        return shape;
    }
}
