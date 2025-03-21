package pr4;

import java.util.SplittableRandom;

public class Probability {
    public static void main(String[] args) {
        int numberOfSamples = 0;
        int numberOfDivisions = 0;

        for (int digit1 = 3; digit1 <= 9; digit1++) {
            for (int digit2 = 2; digit2 <= 8; digit2++) {
                for (int digit3 = 4; digit3 <= 9; digit3++) {
                    for (int digit4 = 1; digit4 <= 6; digit4++) {
                        for (int digit5 = 6; digit5 <= 9; digit5++) {
                            int number = digit1 * 10000 + digit2 * 100 + digit3 * 10 + digit4;

                            numberOfSamples++;

                            if (number % 12 == 0)
                                numberOfDivisions++;
                        }
                    }
                }
            }
        }

        System.out.printf("Total numbers generated: %,d\n", numberOfSamples);
        System.out.printf("Count of numbers divisible by 12: %,d%n", numberOfDivisions);
        System.out.printf("Probability of divisibility by 12: %.4f%n", (double) numberOfDivisions / numberOfSamples);

        System.out.println();

        //2nd solution ...
        anotherApproach();
    }

    public static void anotherApproach() {
        final int numberOfSamples = 1_000_000;
        SplittableRandom rand = new SplittableRandom();

        int numberOfDivisions = 0;

        for (int i = 0; i < numberOfSamples; i++) {
            int firstDigit = rand.nextInt(3, 9 + 1);
            int secondDigit = rand.nextInt(2, 8 + 1);
            int thirdDigit = rand.nextInt(4, 9 + 1);
            int fourthDigit = rand.nextInt(1, 6 + 1);
            int fifthDigit = rand.nextInt(6, 9 + 1);

            int number = firstDigit * 10000 + secondDigit * 1000 + thirdDigit * 100 + fourthDigit * 10 + fifthDigit;

            if (number % 12 == 0)
                numberOfDivisions++;
        }

        System.out.println("Number of divisions: " + numberOfDivisions);
        System.out.printf("Probability: %.4f", (double) numberOfDivisions / numberOfSamples);
    }
}
