package decorator.creators;

import decorator.ShapeCreator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SquareShapeCreator implements ShapeCreator {

    @Override
    public Shape createShape() {
        Shape shape = new Rectangle(100, 100, Color.RED);
        shape.setStroke(Color.BLACK);
        return shape;
    }
}
