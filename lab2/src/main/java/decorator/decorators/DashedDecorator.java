package decorator.decorators;

import decorator.ShapeCreator;
import javafx.scene.shape.Shape;

public class DashedDecorator extends ShapeCreatorDecorator {

    public DashedDecorator(ShapeCreator shapeCreator) {
        super(shapeCreator);
    }

    @Override
    public Shape createShape() {
        Shape shape = wrappedShapeCreator.createShape();
        shape.getStrokeDashArray().addAll(16.0, 14.0, 12.0, 10.0, 8.0);
        return shape;
    }
}
