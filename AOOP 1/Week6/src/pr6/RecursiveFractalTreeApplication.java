package pr6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
// Abbreviaton key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name>

//With the help of AI and internet

public class RecursiveFractalTreeApplication extends Application {

    private final Canvas canvas = new Canvas(600, 600);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        TextField orderField = new TextField("0");
        orderField.setPrefWidth(50);

        orderField.setOnAction(e -> {
            try {
                int order = Integer.parseInt(orderField.getText());
                drawTree(order + 1);
            } catch (NumberFormatException ex) {
                orderField.setText("0");
                drawTree(0);
            }
        });

        HBox controlBox = new HBox(10, new Label("Enter an order:"), orderField);
        controlBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setBottom(controlBox);

        drawTree(0);

        Scene scene = new Scene(root);

        stage.setTitle("Recursive tree");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    private void drawTree(int order) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        drawBranch(gc, canvas.getWidth() / 2, canvas.getHeight() - 50, -90, order);
    }

    private void drawBranch(GraphicsContext gc, double x1, double y1, double angle, int order) {
        if (order == 0) return;

        double length = order * 10;
        double x2 = x1 + Math.cos(Math.toRadians(angle)) * length;
        double y2 = y1 + Math.sin(Math.toRadians(angle)) * length;

        gc.strokeLine(x1, y1, x2, y2);

        drawBranch(gc, x2, y2, angle - 20, order - 1);
        drawBranch(gc, x2, y2, angle + 20, order - 1);
    }
}