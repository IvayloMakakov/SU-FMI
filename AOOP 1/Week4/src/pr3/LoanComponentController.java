package pr3;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class LoanComponentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnShowTable;

    @FXML
    private TextArea txtFieldResult;

    @FXML
    private TextField txtLoanAmount;

    @FXML
    private TextField txtNumberOfYears;

    private LoanComponent loanComponent;

    @FXML
    void btnShowTableOnClick(ActionEvent event) {
        txtFieldResult.setText(String.format("%-15s%-20s%-20s%n", "Interest Rate", "Monthly Payment", "Total Payment"));
        for (double rate = 5; rate <= 8; rate += 1 / 8.0) {
            loanComponent = new LoanComponent(rate, Double.parseDouble(txtNumberOfYears.getText()), Double.parseDouble(txtLoanAmount.getText()));
            txtFieldResult.appendText(String.format("%-15.3f    %-20.2f         %-20.2f%n", rate, loanComponent.getMonthlyPayment(), loanComponent.getTotalAmount()));
        }
    }

    @FXML
    void initialize() {
        assert btnShowTable != null : "fx:id=\"btnShowTable\" was not injected: check your FXML file 'loan component-view.fxml'.";
        assert txtFieldResult != null : "fx:id=\"txtFieldResult\" was not injected: check your FXML file 'loan component-view.fxml'.";
        assert txtLoanAmount != null : "fx:id=\"txtLoanAmount\" was not injected: check your FXML file 'loan component-view.fxml'.";
        assert txtNumberOfYears != null : "fx:id=\"txtNumberOfYears\" was not injected: check your FXML file 'loan component-view.fxml'.";
    }

}
