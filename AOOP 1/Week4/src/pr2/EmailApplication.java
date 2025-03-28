package pr2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class EmailApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*Тук не съм слагал Alert и съответно валидация, понеже информацията,
        която се е въвела искам да се принтира в суров вид*/

        Parent root
                = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("email-view.fxml")));

        Scene scene = new Scene(root);

        stage.setTitle("Email Application");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}