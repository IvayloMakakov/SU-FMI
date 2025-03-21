package pr6b;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

// Abbreviaton key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name> 

public class Canvas extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 500, 450);

        double width = scene.getWidth();
        double height = scene.getHeight();
        Line horizontalDiagonal;

        for (int i = 0; i <= width; i += 20) {
            horizontalDiagonal = new Line(0, i, i, height);
            horizontalDiagonal.setStroke(Color.TRANSPARENT);
            horizontalDiagonal.setStroke(Color.SANDYBROWN);

            group.getChildren().add(horizontalDiagonal);

            horizontalDiagonal = new Line(width, i, i, 0);
            horizontalDiagonal.setStroke(Color.TRANSPARENT);
            horizontalDiagonal.setStroke(Color.SANDYBROWN);

            group.getChildren().add(horizontalDiagonal);

            horizontalDiagonal = new Line(0, height - i, i, 0);
            horizontalDiagonal.setStroke(Color.TRANSPARENT);
            horizontalDiagonal.setStroke(Color.SANDYBROWN);

            group.getChildren().add(horizontalDiagonal);

            horizontalDiagonal = new Line(width, i, width - i, height);
            horizontalDiagonal.setStroke(Color.TRANSPARENT);
            horizontalDiagonal.setStroke(Color.SANDYBROWN);

            group.getChildren().add(horizontalDiagonal);
        }

        stage.setTitle("Canvas Demo");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}