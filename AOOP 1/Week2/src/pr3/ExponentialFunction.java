package pr3;

import java.util.Scanner;

public class ExponentialFunction {
    public static double calculateExponential(double x) {
        double sum = 1.0, term = 1.0, i = 1;

        while (Math.abs(term) > 0.001) {
            term *= x / i;
            sum += term;
            i++;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете стойност за x: ");
        int x = scanner.nextInt();

        double result = calculateExponential(x);
        System.out.printf("Приближена стойност на e^%s е: %.5f%n", x, result);
    }
}
