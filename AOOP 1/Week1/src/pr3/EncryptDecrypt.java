package pr3;

import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class EncryptDecrypt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a four-digit integer: ");

        int n = sc.nextInt();

        if (!(n >= 1000 && n <= 9999)) {
            System.out.println("Not a four-digit number!");
            System.exit(0);
        }

        int encrypt = encrypt(n);
        System.out.printf("%d encrypted: %04d%n", n, encrypt);
        System.out.printf("%04d decrypted: %04d%n",
                encrypt, decrypt(encrypt));
    }

    public static int decrypt(int n) {
        int digit4 = (n % 10 + 3) % 10;
        n /= 10;
        int digit3 = (n % 10 + 3) % 10;
        n /= 10;
        int digit2 = (n % 10 + 3) % 10;
        n /= 10;
        int digit1 = (n % 10 + 3) % 10;

        int temp;
        temp = digit1;
        digit1 = digit3;
        digit3 = temp;

        temp = digit2;
        digit2 = digit4;
        digit4 = temp;

        return digit1 * 1000 + digit2 * 100 + digit3 * 10 + digit4;
    }

    public static int encrypt(int n) {
        int digit4 = (n % 10 + 7) % 10;
        n /= 10;
        int digit3 = (n % 10 + 7) % 10;
        n /= 10;
        int digit2 = (n % 10 + 7) % 10;
        n /= 10;
        int digit1 = (n % 10 + 7) % 10;

        int temp;
        temp = digit1;
        digit1 = digit3;
        digit3 = temp;

        temp = digit2;
        digit2 = digit4;
        digit4 = temp;

        return digit1 * 1000 + digit2 * 100 + digit3 * 10 + digit4;
    }
}
