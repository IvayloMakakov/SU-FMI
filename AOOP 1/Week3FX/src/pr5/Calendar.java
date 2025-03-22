package pr5;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
// Abbreviaton key:       fx-drawing-main
// Template description: JavaFX App class for drawing
// Variables: CLASS_NAME must be assigned clipboard() expression
// 1. Create a Java class
// 2. Copy the class name in the Clipboard (^C)
// 3, Overwrite all  the class contents by running this Live template
// 4. Right-click the class name (should be the same as in the originally created class)
// 5. Select Show Content actions and execute Set package name to ...<your package name> 

public class Calendar extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 500, 450);

        //Приемам, че потребителят ще въвежда валидни данни!

        TextInputDialog dialogYear = new TextInputDialog();
        dialogYear.setTitle("Enter values");
        dialogYear.setHeaderText(null);
        dialogYear.setContentText("Enter year");
        int year = Integer.parseInt(dialogYear.showAndWait().get());

        TextInputDialog dialogMonth = new TextInputDialog();
        dialogMonth.setTitle("Enter values");
        dialogMonth.setHeaderText(null);
        dialogMonth.setContentText("Enter month");
        int month = Integer.parseInt(dialogMonth.showAndWait().get());

        LocalDate date = LocalDate.of(year, month, 1);

        String[] weekDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < 7; i++) {
            Text dayText = new Text(weekDays[i]);
            dayText.setX(50 + i * 50);
            dayText.setY(50);
            dayText.setFont(Font.font(15));
            dayText.setStrokeWidth(5);
            group.getChildren().add(dayText);
        }

        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        DayOfWeek firstDayOfWeek = firstDayOfMonth.getDayOfWeek();
        int startingDay = firstDayOfWeek.getValue();

        int dayOfMonth = 1;
        int row = 1;
        for (int col = startingDay - 1; dayOfMonth <= daysInMonth; col++) {
            if (col == 7) {
                col = 0;
                row++;
            }

            Text dayText = new Text(String.valueOf(dayOfMonth));
            dayText.setX(50 + col * 50);
            dayText.setY(50 + row * 30);
            dayText.setFont(Font.font(15));
            dayText.setStrokeWidth(5);
            group.getChildren().add(dayText);
            dayOfMonth++;
        }

        stage.setTitle(String.format("Calendar for %s %d", date.getMonth().toString(), date.getYear()));
        stage.sizeToScene();
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setScene(scene);
        stage.show();
    }
}