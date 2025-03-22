package pr6;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Random;

public class Fridays {
    public static void main(String[] args) {
        printListOfAllFirday13(new Random().nextInt(1900, 2020 + 1));
    }

    public static void printListOfAllFirday13(int year) {
        LocalDate date;
        for (var month = 1; month <= 12; month++) {
            date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY)
                System.out.println(date);
        }
    }
}
