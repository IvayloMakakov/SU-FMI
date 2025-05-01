package pr8;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CountingSortController {

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

    private Random random;

    private int[] arrayToSort;

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnSortOnClick(ActionEvent event) {
        if (txfAscendingOrderYy.getText().toLowerCase().equals("y")) {
            sort(arrayToSort, true);
        } else {
            sort(arrayToSort, false);
        }

        txaSortedArray.setText(Arrays.toString(arrayToSort));
    }

    //should be private
    public void sort(int[] arrayToSort, boolean sortOrder) {
        if (arrayToSort == null || arrayToSort.length == 0) {
            return;
        }

        int max = arrayToSort[0];
        int min = arrayToSort[0];
        for (int num : arrayToSort) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int range = max - min + 1;

        int[] count = new int[range];
        for (int num : arrayToSort) {
            count[num - min]++;
        }

        int index = 0;
        if (sortOrder) {
            for (int i = 0; i < range; i++) {
                while (count[i]-- > 0) {
                    arrayToSort[index++] = i + min;
                }
            }
        } else {
            for (int i = range - 1; i >= 0; i--) {
                while (count[i]-- > 0) {
                    arrayToSort[index++] = i + min;
                }
            }
        }
    }

    @FXML
    void initialize() {
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert btnSort != null : "fx:id=\"btnSort\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert txaSortedArray != null : "fx:id=\"txaSortedArray\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert txaUnsortedArray != null : "fx:id=\"txaUnsortedArray\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert txfAscendingOrderYy != null : "fx:id=\"txfAscendingOrderYy\" was not injected: check your FXML file 'counting-sort-view.fxml'.";

        this.random = new Random();

        int count = random.nextInt(35);
        this.arrayToSort = new int[count];

        txaUnsortedArray.setText("");

        for (int i = 0; i < count; i++) {
            int curr = random.nextInt(-999, 999 + 1);

            this.arrayToSort[i] = curr;
            txaUnsortedArray.appendText(curr + " ");
        }
    }
}