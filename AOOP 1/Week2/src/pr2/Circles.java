package pr2;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Circles extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 300, 250);

        // TODO  Type code for Java FX drawing objects

        double width = scene.getWidth();
        double height = scene.getHeight();

        final int CIRCLES_COUNT = 10;
        int radius = 10;
        for (int i = 0; i < CIRCLES_COUNT; i++) {
            Circle circle = new Circle(width / 2, height / 2, radius + i * radius);
            circle.setFill(Color.TRANSPARENT);
            circle.setStroke(Color.BLACK);
            group.getChildren().add(circle);
        }

        double x1 = width / 2 - CIRCLES_COUNT * radius;
        double x2 = width / 2 + CIRCLES_COUNT * radius;

        Line horizontalDiagonal = new Line(x1, height / 2, x2, height / 2);
        horizontalDiagonal.setStroke(Color.RED);
        group.getChildren().add(horizontalDiagonal);

        double y1 = height / 2 - CIRCLES_COUNT * radius;
        double y2 = height / 2 + CIRCLES_COUNT * radius;

        Line verticalDiagonal = new Line(width / 2, y1, width / 2, y2);
        verticalDiagonal.setStroke(Color.RED);
        group.getChildren().add(verticalDiagonal);

        // end TODO
        stage.setTitle("Concentric Circles"); // Update Title as required
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}