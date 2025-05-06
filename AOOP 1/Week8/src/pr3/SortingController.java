package pr3;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SortingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnSort;

    @FXML
    private TextArea txaSortedArray;

    @FXML
    private TextArea txaUnsortedArray;

    @FXML
    private TextField txfAscendingOrderYy;

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnSortOnClick(ActionEvent event) {
        String[] splitted = txaUnsortedArray.getText().split("\\s+");

        int[] array = new int[splitted.length];
        for (int i = 0; i < splitted.length; i++) {
            array[i] = Integer.parseInt(splitted[i]);
        }

        Arrays.sort(array);

        if (txfAscendingOrderYy.getText().matches("[Yy]{1}")) {
            txaSortedArray.setText(Arrays.toString(array));
        } else {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("[");
            for (int i = array.length - 1; i > 0; i--) {
                stringBuilder.append(array[i]).append(" ,");
            }
            stringBuilder.append(array[0]);
            stringBuilder.append("]");

            txaSortedArray.setText(stringBuilder.toString());
        }
    }

    @FXML
    void initialize() {
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'sorting-view.fxml'.";
        assert btnSort != null : "fx:id=\"btnSort\" was not injected: check your FXML file 'sorting-view.fxml'.";
        assert txaSortedArray != null : "fx:id=\"txaSortedArray\" was not injected: check your FXML file 'sorting-view.fxml'.";
        assert txaUnsortedArray != null : "fx:id=\"txaUnsortedArray\" was not injected: check your FXML file 'sorting-view.fxml'.";
        assert txfAscendingOrderYy != null : "fx:id=\"txfAscendingOrderYy\" was not injected: check your FXML file 'sorting-view.fxml'.";

    }

}