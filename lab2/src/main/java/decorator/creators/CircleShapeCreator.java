package decorator.creators;

import decorator.ShapeCreator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleShapeCreator implements ShapeCreator {

    @Override
    public Shape createShape() {
        Shape shape = new Circle(50, Color.RED);
        shape.setStroke(Color.BLACK);
        return shape;
    }
}
