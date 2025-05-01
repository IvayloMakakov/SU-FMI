package pr2b;

import java.util.Scanner;

public class TransCipherTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine();

        System.out.print("Enter cipher key: ");
        String cipherKey = sc.nextLine();

        TransCipher cipher = new TransCipher(plainText, cipherKey);

        String cipherEncrypted = cipher.encrypt();
        System.out.println(cipherEncrypted);
        System.out.println(cipher.decrypt(cipherEncrypted));
    }
}
