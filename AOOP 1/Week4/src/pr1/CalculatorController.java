package pr1;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnClearAll;

    @FXML
    private Button btnDiv;

    @FXML
    private Button btnDot;

    @FXML
    private Button btnDoubleZero;

    @FXML
    private Button btnEight;

    @FXML
    private Button btnEqual;

    @FXML
    private Button btnFive;

    @FXML
    private Button btnFour;

    @FXML
    private Button btnM;

    @FXML
    private Button btnMC;

    @FXML
    private Button btnMMinus;

    @FXML
    private Button btnMPlus;

    @FXML
    private Button btnMinus;

    @FXML
    private Button btnMult;

    @FXML
    private Button btnNine;

    @FXML
    private Button btnOff;

    @FXML
    private Button btnOne;

    @FXML
    private Button btnPlus;

    @FXML
    private Button btnSeven;

    @FXML
    private Button btnSix;

    @FXML
    private Button btnThree;

    @FXML
    private Button btnTwo;

    @FXML
    private Button btnZero;

    @FXML
    private TextField txtResult;

    private String firstInputNumber;
    private String secondInputNumber;
    private char sign;

    private String inputNumberMPlus = "0";
    private String inputNumberMMinus = "0";
    private String inputNumberM;

    private String getInputFirstNumber() {
        firstInputNumber = txtResult.getText();
        return firstInputNumber;
    }

    private String getInputSecondNumber() {
        secondInputNumber = txtResult.getText();
        return secondInputNumber;
    }

    private void numberReplacingCode(int number) {
        String input = txtResult.getText();
        if (input.equals("0")) {
            txtResult.setText(number + "");
        } else {
            txtResult.setText(input + number);
        }
    }

    @FXML
    public void btnClearAllOnClick(ActionEvent event) {
        btnClearOnClick(event);
        sign = ' ';
        firstInputNumber = secondInputNumber = "";
    }

    @FXML
    public void btnClearOnClick(ActionEvent event) {
        txtResult.clear();
    }

    @FXML
    public void btnDivOnClick(ActionEvent event) {
        getInputFirstNumber();
        txtResult.clear();
        sign = '/';
    }

    @FXML
    public void btnDotOnClick(ActionEvent event) {
        if (!txtResult.getText().contains(".")) {
            txtResult.setText(txtResult.getText() + ".");
        }
    }

    @FXML
    public void btnDoubleZeroOnClick(ActionEvent event) {
        numberReplacingCode(0);
        numberReplacingCode(0);
    }

    @FXML
    public void btnEightOnClick(ActionEvent event) {
        numberReplacingCode(8);
    }

    @FXML
    public void btnEqualOnClick(ActionEvent event) {
        getInputSecondNumber();

        if (firstInputNumber == null || secondInputNumber == null || firstInputNumber.equals("") || secondInputNumber.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Enter a valid number");
            alert.showAndWait();
            return;
        }

        double num1 = Double.parseDouble(firstInputNumber);
        double num2 = Double.parseDouble(secondInputNumber);

        switch (sign) {
            case '+':
                txtResult.setText(Double.toString(num1 + num2));
                break;
            case '-':
                txtResult.setText(Double.toString(num1 - num2));
                break;
            case '*':
                txtResult.setText(Double.toString(num1 * num2));
                break;
            case '/':
                txtResult.setText(Double.toString(num1 / num2));
                break;
        }
    }

    @FXML
    public void btnFiveOnClick(ActionEvent event) {
        numberReplacingCode(5);
    }

    @FXML
    public void btnFourOnClick(ActionEvent event) {
        numberReplacingCode(4);
    }

    @FXML
    public void btnMCOnClick(ActionEvent event) {
        inputNumberMPlus = inputNumberMMinus = inputNumberM = null;
    }

    @FXML
    public void btnMMinusOnClick(ActionEvent event) {
        if (inputNumberM != null) {
            txtResult.setText(Double.toString(Double.parseDouble(inputNumberM) - Double.parseDouble(getInputFirstNumber())));
        }
    }

    @FXML
    public void btnMOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("You saved your number!");
        alert.showAndWait();
        inputNumberM = getInputFirstNumber();
        txtResult.clear();
    }

    @FXML
    public void btnMPlusOnClick(ActionEvent event) {
        if (inputNumberM != null) {
            txtResult.setText(Double.toString(Double.parseDouble(inputNumberM) + Double.parseDouble(getInputFirstNumber())));
        }
    }

    @FXML
    public void btnMinusOnClick(ActionEvent event) {
        getInputFirstNumber();
        txtResult.clear();
        sign = '-';
    }

    @FXML
    public void btnMultOnClick(ActionEvent event) {
        getInputFirstNumber();
        txtResult.clear();
        sign = '*';
    }

    @FXML
    public void btnNineOnClick(ActionEvent event) {
        numberReplacingCode(9);
    }

    @FXML
    public void btnOffOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void btnOneOnClick(ActionEvent event) {
        numberReplacingCode(1);
    }

    @FXML
    public void btnPlusOnClick(ActionEvent event) {
        getInputFirstNumber();
        txtResult.clear();
        sign = '+';
    }

    @FXML
    public void btnSevenOnClick(ActionEvent event) {
        numberReplacingCode(7);
    }

    @FXML
    public void btnSixOnClick(ActionEvent event) {
        numberReplacingCode(6);
    }

    @FXML
    public void btnThreeOnClick(ActionEvent event) {
        numberReplacingCode(3);
    }

    @FXML
    public void btnTwoOnClick(ActionEvent event) {
        numberReplacingCode(2);
    }

    @FXML
    public void btnZeroOnClick(ActionEvent event) {
        numberReplacingCode(0);
    }

    @FXML
    public void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnClearAll != null : "fx:id=\"btnClearAll\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnDiv != null : "fx:id=\"btnDiv\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnDot != null : "fx:id=\"btnDot\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnDoubleZero != null : "fx:id=\"btnDoubleZero\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnEight != null : "fx:id=\"btnEight\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnEqual != null : "fx:id=\"btnEqual\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnFive != null : "fx:id=\"btnFive\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnFour != null : "fx:id=\"btnFour\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnM != null : "fx:id=\"btnM\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnMC != null : "fx:id=\"btnMC\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnMMinus != null : "fx:id=\"btnMMinus\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnMPlus != null : "fx:id=\"btnMPlus\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnMinus != null : "fx:id=\"btnMinus\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnMult != null : "fx:id=\"btnMult\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnNine != null : "fx:id=\"btnNine\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnOff != null : "fx:id=\"btnOff\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnOne != null : "fx:id=\"btnOne\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnPlus != null : "fx:id=\"btnPlus\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnSeven != null : "fx:id=\"btnSeven\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnSix != null : "fx:id=\"btnSix\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnThree != null : "fx:id=\"btnThree\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnTwo != null : "fx:id=\"btnTwo\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert btnZero != null : "fx:id=\"btnZero\" was not injected: check your FXML file 'calculator-view.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'calculator-view.fxml'.";

    }

}
