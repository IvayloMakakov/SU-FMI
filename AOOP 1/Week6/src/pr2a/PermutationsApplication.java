package pr2a;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class PermutationsApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root
                = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("permutations-view.fxml")));

        Scene scene = new Scene(root);

        stage.setTitle("Permutations");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}