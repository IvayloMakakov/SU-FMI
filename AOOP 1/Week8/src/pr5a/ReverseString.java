package pr5a;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");
        String str = sc.nextLine();

        reverseDisplay(str);
    }

    public static void reverseDisplay(String value) {
        if (value == null || value.isEmpty()) {
            return;
        }

        System.out.print(value.charAt(value.length() - 1));
        reverseDisplay(value.substring(0,value.length() - 1));
    }
}
