package pr4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConverterController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBinary;

    @FXML
    private Button btnDecimal;

    @FXML
    private Button btnHex;

    @FXML
    private MenuItem mnuBinary;

    @FXML
    private MenuItem mnuDecimal;

    @FXML
    private MenuItem mnuHex;

    @FXML
    private Menu mnuQuit;

    @FXML
    private MenuItem mnuQuitApplication;

    @FXML
    private TextField txtBinary;

    @FXML
    private TextField txtDecimal;

    @FXML
    private TextField txtHex;

    private void fromBinary() {
        String input = txtBinary.getText();
        if (input == null || input.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid binary number!");
            alert.showAndWait();
        } else {
            int number = Integer.parseInt(input, 2);
            txtDecimal.setText(Integer.toString(number));
            txtHex.setText(Integer.toHexString(number));
        }
    }

    private void fromDecimal() {
        String input = txtDecimal.getText();
        if (input == null || input.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid decimal number!");
            alert.showAndWait();
        } else {
            int number = Integer.parseInt(input);
            txtHex.setText(Integer.toHexString(number));
            txtBinary.setText(Integer.toBinaryString(number));
        }
    }

    private void fromHex() {
        String input = txtHex.getText();
        if (input == null || input.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid hex number!");
            alert.showAndWait();
        } else {
            int number = Integer.parseInt(input, 16);
            txtDecimal.setText(Integer.toString(number));
            txtBinary.setText(Integer.toBinaryString(number));
        }
    }


    @FXML
    void btnBinaryOnClick(ActionEvent event) {
        fromBinary();
    }

    @FXML
    void btnDecimalOnClick(ActionEvent event) {
        fromDecimal();
    }

    @FXML
    void btnHexOnClick(ActionEvent event) {
        fromHex();
    }

    @FXML
    void mnuBinaryOnClick(ActionEvent event) {
        fromBinary();
    }

    @FXML
    void mnuDecimalOnClick(ActionEvent event) {
        fromDecimal();
    }

    @FXML
    void mnuHexOnClick(ActionEvent event) {
        fromHex();
    }

    @FXML
    void mnuQuitApplicationOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void mnuQuitOnClick(ActionEvent event) {
        //empty
    }

    @FXML
    void txtBinaryOnClick(ActionEvent event) {
        fromBinary();
    }

    @FXML
    void txtDecimalOnClick(ActionEvent event) {
        fromDecimal();
    }

    @FXML
    void txtHexOnClick(ActionEvent event) {
        fromHex();
    }

    @FXML
    void initialize() {
        assert btnBinary != null : "fx:id=\"btnBinary\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert btnDecimal != null : "fx:id=\"btnDecimal\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert btnHex != null : "fx:id=\"btnHex\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert mnuBinary != null : "fx:id=\"mnuBinary\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert mnuDecimal != null : "fx:id=\"mnuDecimal\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert mnuHex != null : "fx:id=\"mnuHex\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert mnuQuit != null : "fx:id=\"mnuQuit\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert mnuQuitApplication != null : "fx:id=\"mnuQuitApplication\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert txtBinary != null : "fx:id=\"txtBinary\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert txtDecimal != null : "fx:id=\"txtDecimal\" was not injected: check your FXML file 'converter-view.fxml'.";
        assert txtHex != null : "fx:id=\"txtHex\" was not injected: check your FXML file 'converter-view.fxml'.";

    }

}
