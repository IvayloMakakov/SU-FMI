package pr1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrationFormController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRegister;

    @FXML
    private TextField txfConfirmPassword;

    @FXML
    private TextField txfEmail;

    @FXML
    private TextField txfPassword;

    @FXML
    private TextField txfPhoneNumber;

    @FXML
    private TextField txfUsername;

    private Alert alert;

    @FXML
    void btnRegisterOnClick(ActionEvent event) {
        validate();
    }

    private void showAlert(String content) {
        alert.setContentText(content);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void validate() {
        if (txfUsername.getText().isEmpty() || txfEmail.getText().isEmpty() || txfPhoneNumber.getText().isEmpty() || txfConfirmPassword.getText().isEmpty()) {
            showAlert("Please fill all the fields");
        } else if (!txfUsername.getText().matches("[A-Z][a-z]+")) {
            showAlert("Please enter a valid username");
        } else if (!txfPhoneNumber.getText().matches("\\(([0-9]){4}\\)\\(([0-9]){7}\\)")) {
            showAlert("Please enter a valid phone number");
        } else if (!txfEmail.getText().matches("\\w+@\\w+.\\w+")) {
            showAlert("Please enter a valid email");
        } else if (!txfPassword.getText().matches("\\w+")) {
            showAlert("Please enter a valid password");
        } else if (!txfConfirmPassword.getText().equals(txfPassword.getText())) {
            showAlert("Passwords do not match");
        } else {
            showAlert("Thank you!");
        }
    }

    @FXML
    void initialize() {
        assert btnRegister != null : "fx:id=\"btnRegister\" was not injected: check your FXML file 'registration-form-view.fxml'.";
        assert txfConfirmPassword != null : "fx:id=\"txfConfirmPassword\" was not injected: check your FXML file 'registration-form-view.fxml'.";
        assert txfEmail != null : "fx:id=\"txfEmail\" was not injected: check your FXML file 'registration-form-view.fxml'.";
        assert txfPassword != null : "fx:id=\"txfPassword\" was not injected: check your FXML file 'registration-form-view.fxml'.";
        assert txfPhoneNumber != null : "fx:id=\"txfPhoneNumber\" was not injected: check your FXML file 'registration-form-view.fxml'.";
        assert txfUsername != null : "fx:id=\"txfUsername\" was not injected: check your FXML file 'registration-form-view.fxml'.";

        alert = new Alert(Alert.AlertType.INFORMATION);
    }
}