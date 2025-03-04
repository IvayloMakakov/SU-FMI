package pr2;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a five-digit number: ");

        int n = sc.nextInt();

        if (!(n >= 10_000 && n <= 99_999)) {
            System.out.println("Not a valid five-digit number");
            System.exit(0);
        }

        int digit1, digit2, digit4, digit5;
        final int ORIGINAL_NUMBER = n;
        digit5 = n % 10;
        n = n / 10;
        digit4 = n % 10;
        n /= 100;
        digit2 = n % 10;
        digit1 = n / 10;

        String resultString = "";

        if (digit1 == digit5 && digit2 == digit4)
            resultString = String.format("%d is a palindrome.",
                    ORIGINAL_NUMBER);
        else resultString = String.format("%d is NOT a palindrome.", ORIGINAL_NUMBER);

        System.out.println(resultString);
    }
}