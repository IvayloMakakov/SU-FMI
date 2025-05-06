package pr4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LinearSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSearch;

    @FXML
    private TextArea txaArray;

    @FXML
    private TextField txfKey;

    @FXML
    private TextField txfResult;

    private Alert alert;

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnSearchOnClick(ActionEvent event) {
        validate();
    }

    private void showAlert(String contentText) {
        alert.setContentText(contentText);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void validate() {
        if (txaArray.getText().isEmpty() || txfKey.getText().isEmpty()) {
            showAlert("Please enter all the fields");
        } else if (txfKey.getText().matches("\\d+")) {
            search();
        } else {
            showAlert("Please enter a valid key");
        }
    }

    private void search() {
        String[] splitted = txaArray.getText().split("\\s+");

        int[] array = new int[splitted.length];

        txfResult.setText("Search key not found");

        for (int i = 0; i < splitted.length; i++) {
            array[i] = Integer.parseInt(splitted[i]);
            if (array[i] == Integer.parseInt(txfKey.getText())) {
                txfResult.setText("Search key found");
            }
        }
    }

    @FXML
    void initialize() {
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'linear-search-view.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'linear-search-view.fxml'.";
        assert txaArray != null : "fx:id=\"txaArray\" was not injected: check your FXML file 'linear-search-view.fxml'.";
        assert txfKey != null : "fx:id=\"txfKey\" was not injected: check your FXML file 'linear-search-view.fxml'.";
        assert txfResult != null : "fx:id=\"txfResult\" was not injected: check your FXML file 'linear-search-view.fxml'.";

        alert = new Alert(Alert.AlertType.INFORMATION);
    }
}