package vue;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.ResourceBundle;

import blogic.Animal;
import blogic.AnimalInstance;
import blogic.AnimalType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ZooController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDistributionByRegions;

    @FXML
    private Button btnEndangeredByAnimalType;

    @FXML
    private Button btnQuit;

    @FXML
    private Button btnShowSortedByAge;

    @FXML
    private Button btnShuffleSamples;

    @FXML
    private TextArea txaResult;

    private String printSamples() {
        String result = "";
        for (int i = 0; i < samples.length; i++) {
            result += samples[i].toString() + "\n";
        }
        return result;
    }

    private AnimalInstance[] samples;
    private Animal[] animals;

    @FXML
    void btnDistributionByRegionsOnClick(ActionEvent event) {
        int[] countByRegions = new int[Animal.REGIONS.length];

        for (int i = 0; i < samples.length; i++) {
            int index = samples[i].getAnimalKey();
            String region = animals[index].getRegion();

            for (int j = 0; j < Animal.REGIONS.length; j++) {
                if (Animal.REGIONS[j].equals(region)) {
                    countByRegions[j]++;
                }
            }
        }

        String result = "";
        for (int i = 0; i < countByRegions.length; i++) {
            result += Animal.REGIONS[i] + ":" + countByRegions[i] + "\n";
        }

        txaResult.setText(result);
    }

    @FXML
    void btnEndangeredByAnimalTypeOnClick(ActionEvent event) {
        AnimalType[] animalTypes = AnimalType.values();

        int[] countEndangered = new int[animalTypes.length];
        for (int i = 0; i < samples.length; i++) {
            int index = samples[i].getAnimalKey();
            boolean endangered = animals[index].isEndangered();
            if (endangered) {
                countEndangered[animals[index].getAnimalType().ordinal()]++;
            }
        }

        String result = "";
        for (int i = 0; i < countEndangered.length; i++) {
            result += animalTypes[i] + ": " + countEndangered[i] + "\n";
        }

        txaResult.setText(result);
    }

    @FXML
    void btnQuitOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnShowSortedByAgeOnClick(ActionEvent event) {
        AnimalInstance temp;

        for (int i = 0; i < animals.length; i++) {
            for (int j = 0; j < animals.length - i - 1; j++) {
                if (samples[j].getAnimalAge() < samples[j + 1].getAnimalAge()) {
                    temp = samples[j];
                    samples[j] = samples[j + 1];
                    samples[j + 1] = temp;
                }
            }
        }

        txaResult.setText(printSamples());
    }

    @FXML
    void btnShuffleSamplesOnClick(ActionEvent event) {
        Collections.shuffle(Arrays.asList(animals));
        
        txaResult.setText(printSamples());
    }

    @FXML
    void initialize() {
        assert btnDistributionByRegions != null : "fx:id=\"btnDistributionByRegions\" was not injected: check your FXML file 'zoo-view.fxml'.";
        assert btnEndangeredByAnimalType != null : "fx:id=\"btnEndangeredByAnimalType\" was not injected: check your FXML file 'zoo-view.fxml'.";
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'zoo-view.fxml'.";
        assert btnShowSortedByAge != null : "fx:id=\"btnShowSortedByAge\" was not injected: check your FXML file 'zoo-view.fxml'.";
        assert btnShuffleSamples != null : "fx:id=\"btnShuffleSamples\" was not injected: check your FXML file 'zoo-view.fxml'.";
        assert txaResult != null : "fx:id=\"txaResult\" was not injected: check your FXML file 'zoo-view.fxml'.";

        animals = new Animal[]{
                new Animal("Тигър", AnimalType.MAMMAL, true, "Asia", true),
                new Animal("Фламинго", AnimalType.BIRD, false, "Africa", true),
                new Animal("Гущер", AnimalType.REPTILE, true, "Australia", true),
                new Animal("Пъстърва", AnimalType.FISH, false, "Europe", false),
                new Animal("Делфин", AnimalType.MAMMAL, false, "Australia", true),
                new Animal("Вълк", AnimalType.MAMMAL, true, "America", false)
        };

        samples = new AnimalInstance[20];
        Random random = new Random();

        int animalKey;
        String animalName;
        int animalAge;
        for (int i = 0; i < samples.length; i++) {
            animalKey = random.nextInt(animals.length);
            animalName = animals[animalKey].getName();
            animalAge = random.nextInt(10, 31);
            samples[i] = new AnimalInstance(animalKey, animalName, animalAge);
        }
    }
}