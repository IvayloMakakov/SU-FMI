package pr4;

import java.util.Scanner;

public class Nucleotide {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a four-digit integer: ");

        int n = sc.nextInt();

        if (!(n >= 1000 && n <= 9999)) {
            System.out.println("Not a four-digit number!");
            System.exit(0);
        }

        String nucleotide = "";

        while (n > 0) {
            int digit = n % 4;
            char letter;
            if (digit == 0) {
                letter = 'A';
            } else if (digit == 1) {
                letter = 'C';
            } else if (digit == 2) {
                letter = 'G';
            } else {
                letter = 'T';
            }
            nucleotide = String.format("%c%s", letter, nucleotide);
            n = n / 4;
        }

        System.out.println(nucleotide);
    }
}
