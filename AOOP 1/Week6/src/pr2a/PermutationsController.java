package pr2a;

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

public class PermutationsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnComputePermutations;

    @FXML
    private Button btnQuit;

    @FXML
    private TextArea txaPermutations;

    @FXML
    private TextField txfArray;

    private Random rand;

    private ArrayList<Integer> list;

    @FXML
    void btnComputePermutationsOnClick(ActionEvent event) {
        printPermutations(0);
    }

    private void printPermutations(int idx) {
        if (idx == list.size()) {
            txaPermutations.appendText(list.toString() + "\n");
            return;
        }

        for (int i = idx; i < this.list.size(); i++) {
            swap(idx, i);

            printPermutations(idx + 1);

            swap(idx, i);
        }
    }

    private void swap(int i, int j) {
        int temp = this.list.get(i);
        this.list.set(i, this.list.get(j));
        this.list.set(j, temp);
    }

    private void assignRandomNumbersToArray() {
        while (list.size() < 4) {
            int r = rand.nextInt(10);

            if (!list.contains(r)) {
                list.add(r);
            }
        }
    }

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void initialize() {
        assert btnComputePermutations != null : "fx:id=\"btnComputePermutations\" was not injected: check your FXML file 'permutations-view.fxml'.";
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'permutations-view.fxml'.";
        assert txaPermutations != null : "fx:id=\"txaPermutations\" was not injected: check your FXML file 'permutations-view.fxml'.";
        assert txfArray != null : "fx:id=\"txfArray\" was not injected: check your FXML file 'permutations-view.fxml'.";
        this.rand = new Random();
        this.list = new ArrayList<>();

        assignRandomNumbersToArray();

//        Another approach:
//        this.array = new int[4];
//        boolean[] arr = new boolean[10];
//
//        for (int i = 0; i < arr.length; i++) {
//            arr[this.rand.nextInt(0, 9 + 1)] = true;
//        }
//
//        for (int i = 0, j = 0; i < arr.length && j < 4; i++) {
//            if (arr[i]) {
//                this.array[j] = i * (this.rand.nextInt(0, 6 + 1) == 5 ? -1 : 1);
//                j++;
//            }
//        }
//        txfArray.setText(Arrays.toString(this.array));

        txfArray.setText(this.list.toString());
    }
}