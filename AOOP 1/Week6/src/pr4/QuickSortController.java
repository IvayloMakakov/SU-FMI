package pr4;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class QuickSortController {

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

    private ArrayList<Integer> listToSort;

    private boolean sortOrder;

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnSortOnClick(ActionEvent event) {
        if (txfAscendingOrderYy.getText().toLowerCase().equals("y")) {
            this.sortOrder = true;
        }else{
            this.sortOrder = false;
        }

        txaSortedArray.setText(sort(this.listToSort).toString());
    }

    public ArrayList<Integer> sort(ArrayList<Integer> listToSort) {
        quickSort(listToSort, 0, listToSort.size() - 1);
        return listToSort;
    }

    private void quickSort(ArrayList<Integer> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(list, low, high);
            quickSort(list, low, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, high);
        }
    }

    private int partition(ArrayList<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (shouldSwap(list.get(j), pivot)) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);

        return i + 1;
    }

    private boolean shouldSwap(int a, int b) {
        return sortOrder ? a < b : a > b;
    }

    private void swap(ArrayList<Integer> list, int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    @FXML
    void initialize() {
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert btnSort != null : "fx:id=\"btnSort\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert txaSortedArray != null : "fx:id=\"txaSortedArray\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert txaUnsortedArray != null : "fx:id=\"txaUnsortedArray\" was not injected: check your FXML file 'counting-sort-view.fxml'.";
        assert txfAscendingOrderYy != null : "fx:id=\"txfAscendingOrderYy\" was not injected: check your FXML file 'counting-sort-view.fxml'.";

        this.random = new Random();
        this.sortOrder = false;

        int count = random.nextInt(35);
        this.listToSort = new ArrayList<>(count);

        txaUnsortedArray.setText("");

        for (int i = 0; i < count; i++) {
            int curr = random.nextInt(-999, 999 + 1);

            this.listToSort.add(curr);
            txaUnsortedArray.appendText(curr + " ");
        }
    }
}