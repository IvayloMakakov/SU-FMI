package pr1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;
// Abbreviaton key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name>

public class Geometry extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage stage) throws Exception {
        javafx.scene.Group group = new javafx.scene.Group();
        javafx.scene.Scene scene = new javafx.scene.Scene(group, 500, 450);

        double width = scene.getWidth();
        double height = scene.getHeight();

        double radius = Math.min(width, height) / 3;

        Circle circle = new Circle(width / 2, height / 2, radius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.RED);
        circle.setStrokeWidth(3);
        group.getChildren().add(circle);

        double x;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input values");
        dialog.setHeaderText("Input X");
        dialog.setContentText("Enter X");

        do {
            String input = dialog.showAndWait().get();
            x = Double.parseDouble(input);
        } while (x < 0 || x > width);

        Line AB = new Line(x, 0, x, height);
        AB.setStroke(Color.BLUE);
        AB.setStrokeWidth(3);
        group.getChildren().add(AB);

        double b = x - width / 2;
        double aSquared = radius * radius - b * b;

        if (aSquared < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Number of intersections");
            alert.setHeaderText("Number of intersections: 0");
            alert.showAndWait();
        } else if (aSquared == 0) {
            Circle circle1 = new Circle(x, height / 2, 5);
            circle1.setFill(Color.BLACK);
            group.getChildren().add(circle1);
            Text text1 = new Text(x + 5, height / 2, String.format("(%.0f, %.0f)", x, height / 2));
            text1.setFill(Color.BLACK);
            group.getChildren().add(text1);

        } else {
            double a = Math.sqrt(aSquared);
            double yUp = height / 2 - a;
            double yDown = height / 2 + a;

            Circle intersectionUp = new Circle(x, yUp, 5);
            Circle intersectionDown = new Circle(x, yDown, 5);

            Text intersectionUpText = new Text(x + 5, yUp, String.format("(%.0f; %.0f)", x, yUp));
            Text intersectionDownText = new Text(x + 5, yDown, String.format("(%.0f; %.0f)", x, yDown));

            group.getChildren().addAll(intersectionUp, intersectionDown, intersectionUpText, intersectionDownText);
        }

        stage.setTitle("Circle drawing");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}