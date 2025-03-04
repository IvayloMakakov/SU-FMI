package pr1;

import java.util.Scanner;

public class Temperature {
    public static double farenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9.0;
    }

    private static double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5.0) + 32;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 1 for F to C or 2 for C to F: ");

        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Enter the temperature in Fahrenheit: ");
            double fahrenheit = sc.nextDouble();
            double celsius = farenheitToCelsius(fahrenheit);
            System.out.printf("Temperature %fF in C is %.0f%n", fahrenheit, celsius);
        } else if (choice == 2) {
            System.out.print("Enter the temperature in Celsius: ");
            double celsius = sc.nextDouble();
            double fahrenheit = celsiusToFahrenheit(celsius);
            System.out.printf("Temperature %fC in F is %.0f%n", celsius, fahrenheit);
        } else {
            System.out.println("Invalid choice!");
            System.exit(0);
        }

        String name= "Alex";
        String message = STR."Greetings \{ name }!";
        System.out.println(message);
    }
}
