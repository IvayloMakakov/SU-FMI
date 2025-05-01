package pr2a;

import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the plain text: ");
        String plainText = sc.next();
        plainText = plainText.toUpperCase();

        System.out.print("Enter the shift length: ");
        int shiftLength = sc.nextInt();

        String encryptedText = encypt(plainText, shiftLength);
        System.out.println("Encrypted: " + encryptedText);
        System.out.println("Decrypted: " + decrypt(encryptedText, shiftLength));
    }

    public static String encypt(String plainText, int shiftLength) {
        char[] chars = plainText.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((chars[i] - 'A' + shiftLength) % 26 + 'A');
        }

        return new String(chars);
    }

    public static String decrypt(String text, int shiftLength) {
        char[] chars = text.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((chars[i] - 'Z' - shiftLength) % 26 + 'Z');
        }

        return new String(chars);
    }
}
