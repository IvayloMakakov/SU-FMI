package test;

import geometry.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
// Abbreviaton key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name> 

public class GeometryTest extends Application {

    private Random generator = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    public void drawRectangleWithDiagonals(Group group) {
        double[] coords = new double[] {generator.nextDouble(500), generator.nextDouble(500)};
        Point point = new Point(coords);
        double width = generator.nextDouble(10, 100);
        double height = generator.nextDouble(10, 100);

        Rectangle rectangle = new Rectangle(point, width, height);
        Line diagOne = new Line(point, new Point(
                new double[] {coords[0] + width, coords[1] + height}
        ));

        Line diagTwo =
                new Line(
                        new Point(new double[] {coords[0], coords[1] + height}),
                        new Point(new double[] {coords[0] + width, coords[1]}));

        rectangle.draw(group);
        diagOne.draw(group);
        diagTwo.draw(group);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 600, 600);

        drawRectangleWithDiagonals(group);
        drawRectangleWithDiagonals(group);

        stage.setTitle("Geometry");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}