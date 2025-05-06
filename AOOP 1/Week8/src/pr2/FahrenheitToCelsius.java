package pr2;

import java.util.Scanner;

public class FahrenheitToCelsius {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Fahrenheit: ");
        String fahrenheit = sc.nextLine();

        while (!fahrenheit.matches("^[-+]?[0-9]*\\.?[0-9]+$")) {
            System.out.print("Please enter a valid number: ");
            fahrenheit = sc.nextLine();
        }

        System.out.printf("Celsius: %.2f", 5.0 / 9 * (Double.parseDouble(fahrenheit) - 32));
    }
}