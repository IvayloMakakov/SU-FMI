package pr3;

import java.util.Scanner;

public class BarCodeTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter zip code: ");
        byte zipCode = sc.nextByte();

        System.out.println(new BarCode().decode(zipCode));
    }
}
