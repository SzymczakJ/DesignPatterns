package decorator.decorators;

import decorator.ShapeCreator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class BlueDecorator extends ShapeCreatorDecorator {

    public BlueDecorator(ShapeCreator shapeCreator) {
        super(shapeCreator);
    }

    @Override
    public Shape createShape() {
        Shape shape = wrappedShapeCreator.createShape();
        shape.setFill(Color.BLUE);
        return shape;
    }
}
