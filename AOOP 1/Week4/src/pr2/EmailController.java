package pr2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EmailController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSend;

    @FXML
    private TextField txtBcc;

    @FXML
    private TextField txtCC;

    @FXML
    private TextField txtMessageContent;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtTo;

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnSendOnClick(ActionEvent event) {
        String bcc = txtBcc.getText();
        String cc = txtCC.getText();
        String message = txtMessageContent.getText();
        String subject = txtSubject.getText();
        String to = txtTo.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Email Application");
        alert.setHeaderText(null);
        alert.setContentText(String.format("To: %s\nCC: %s\nBcc: %s\nSubject: %s\nContent: %s", to, cc, bcc, subject, message));

        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'email-view.fxml'.";
        assert btnSend != null : "fx:id=\"btnSend\" was not injected: check your FXML file 'email-view.fxml'.";
        assert txtBcc != null : "fx:id=\"txtBcc\" was not injected: check your FXML file 'email-view.fxml'.";
        assert txtCC != null : "fx:id=\"txtCC\" was not injected: check your FXML file 'email-view.fxml'.";
        assert txtMessageContent != null : "fx:id=\"txtMessageContent\" was not injected: check your FXML file 'email-view.fxml'.";
        assert txtSubject != null : "fx:id=\"txtSubject\" was not injected: check your FXML file 'email-view.fxml'.";
        assert txtTo != null : "fx:id=\"txtTo\" was not injected: check your FXML file 'email-view.fxml'.";

    }

}
