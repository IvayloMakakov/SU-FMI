package pr4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ConverterApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*В условието е поставено да се реализира конвертирането при натискане на enter, но понеже има бутони и меню,
         * същите функции се изпълняват и от тях.
         * Вместо грид => VBox*/

        Parent root
                = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("converter-view.fxml")));

        Scene scene = new Scene(root);

        stage.setTitle("Numeric converter");
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}