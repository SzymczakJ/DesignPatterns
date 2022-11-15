package decorator.decorators;

import decorator.ShapeCreator;
import javafx.scene.shape.Shape;

public abstract class ShapeCreatorDecorator implements ShapeCreator {

    protected ShapeCreator wrappedShapeCreator;


    public ShapeCreatorDecorator(ShapeCreator shapeCreator) {
        wrappedShapeCreator = shapeCreator;
    }

    @Override
    public Shape createShape() {
        return wrappedShapeCreator.createShape();
    }
}
