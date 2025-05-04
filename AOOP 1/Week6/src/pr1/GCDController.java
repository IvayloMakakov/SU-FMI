package pr1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GCDController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnComputeGCD;

    @FXML
    private Button btnQuit;

    @FXML
    private TextField txfA;

    @FXML
    private TextField txfB;

    @FXML
    private TextField txfGCD;

    @FXML
    void btnComputeGCDOnClick(ActionEvent event) {
        int a = Integer.parseInt(txfA.getText());
        int b = Integer.parseInt(txfB.getText());

        txfGCD.setText(String.format("%d", getGCD(a, b)));
    }

    private int getGCD(int a, int b) {
        return b == 0 ? a : getGCD(b, a % b);
    }

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void initialize() {
        assert btnComputeGCD != null : "fx:id=\"btnComputeGCD\" was not injected: check your FXML file 'gcd-view.fxml'.";
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'gcd-view.fxml'.";
        assert txfA != null : "fx:id=\"txfA\" was not injected: check your FXML file 'gcd-view.fxml'.";
        assert txfB != null : "fx:id=\"txfB\" was not injected: check your FXML file 'gcd-view.fxml'.";
        assert txfGCD != null : "fx:id=\"txfGCD\" was not injected: check your FXML file 'gcd-view.fxml'.";

    }
}