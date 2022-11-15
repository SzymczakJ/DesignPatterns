package decorator;

import decorator.creators.CircleShapeCreator;
import decorator.creators.SquareShapeCreator;
import decorator.creators.TriangleShapeCreator;
import decorator.decorators.BlueDecorator;
import decorator.decorators.DashedDecorator;
import decorator.decorators.DottedDecorator;
import decorator.decorators.GreenDecorator;
import decorator.enums.Colours;
import decorator.enums.LineStyle;
import decorator.enums.Shapes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


public class App extends Application {

    private Shapes shape = Shapes.TRIANGLE;
    private Colours colour = Colours.RED;
    private LineStyle lineStyle = LineStyle.SOLID;
    private VBox buttonsVBox = new VBox();
    private HBox generalHBox = new HBox();
    private Shape shapeToDraw;
    private VBox shapeVBox = new VBox();
    private ShapeCreator shapeCreator = new TriangleShapeCreator();


    @Override
    public void start(Stage primaryStage) throws Exception {

        buttonsVBox = arrangeButtons();
        shapeToDraw = shapeCreator.createShape();
        shapeVBox.getChildren().add(shapeToDraw);
        generalHBox = new HBox(buttonsVBox, shapeVBox);
        Scene scene = new Scene(generalHBox, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox arrangeButtons() {
        Button triangleButton = new Button("triangle");
        triangleButton.setOnAction(event -> {
            shape = Shapes.TRIANGLE;
            refreshShape();
        });
        Button squareButton = new Button("square");
        squareButton.setOnAction(event -> {
            shape = Shapes.SQUARE;
            refreshShape();
        });
        Button circleButton = new Button("circle");
        circleButton.setOnAction(event -> {
            shape = Shapes.CIRCLE;
            refreshShape();
        });
        Button redButton = new Button("red");
        redButton.setOnAction(event -> {
            colour = Colours.RED;
            refreshShape();
        });
        Button greenButton = new Button("green");
        greenButton.setOnAction(event -> {
            colour = Colours.GREEN;
            refreshShape();
        });
        Button blueButton = new Button("blue");
        blueButton.setOnAction(event -> {
            colour = Colours.BLUE;
            refreshShape();
        });
        Button solidButton = new Button("solid");
        solidButton.setOnAction(event -> {
            lineStyle = LineStyle.SOLID;
            refreshShape();
        });
        Button dottedButton = new Button("dotted");
        dottedButton.setOnAction(event -> {
            lineStyle = LineStyle.DOTTED;
            refreshShape();
        });
        Button dashedButton = new Button("dashed");
        dashedButton.setOnAction(event -> {
            lineStyle = LineStyle.DASHED;
            refreshShape();
        });

        HBox shapesHBox = new HBox(triangleButton, squareButton, circleButton);
        HBox colourHBox = new HBox(redButton, greenButton, blueButton);
        HBox lineStyleHBox = new HBox(solidButton, dottedButton, dashedButton);

        return new VBox(shapesHBox, colourHBox, lineStyleHBox);
    }

    private void refreshShape() {
        switch (shape) {
            case TRIANGLE -> {
                shapeCreator = new TriangleShapeCreator();
            }
            case SQUARE -> {
                shapeCreator = new SquareShapeCreator();
            }
            case CIRCLE -> {
                shapeCreator = new CircleShapeCreator();
            }
        }
        switch (colour) {
            case GREEN -> {
                shapeCreator = new GreenDecorator(shapeCreator);
            }
            case BLUE -> shapeCreator = new BlueDecorator(shapeCreator);
        }
        switch (lineStyle) {
            case DASHED -> shapeCreator = new DashedDecorator(shapeCreator);
            case DOTTED -> shapeCreator = new DottedDecorator(shapeCreator);
        }
        shapeToDraw = shapeCreator.createShape();
        shapeVBox.getChildren().clear();
        shapeVBox.getChildren().add(shapeToDraw);

    }
}
