package decorator.creators;

import decorator.ShapeCreator;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class TriangleShapeCreator implements ShapeCreator {

    @Override
    public Shape createShape() {
        Shape triiangle =  createTriangle(new Point2D(0, 0), new Point2D(0, 200), new Point2D(200, 0));
        triiangle.setFill(Color.RED);
        triiangle.setStroke(Color.BLACK);
        return triiangle;
    }

    Shape createTriangle(Point2D p1, Point2D p2, Point2D p3){
        Point2D centre = p1.midpoint(p2).midpoint(p3);
        Point2D p1Corrected = p1.subtract(centre);
        Point2D p2Corrected = p2.subtract(centre);
        Point2D p3Corrected = p3.subtract(centre);
        Polygon polygon = new Polygon(
                p1Corrected.getX(), p1Corrected.getY(),
                p2Corrected.getX(), p2Corrected.getY(),
                p3Corrected.getX(), p3Corrected.getY()
        );
        polygon.setLayoutX(centre.getX());
        polygon.setLayoutY(centre.getY());
        return polygon;
    }
}
