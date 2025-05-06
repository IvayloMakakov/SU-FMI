package pr7;

import java.util.Scanner;

public class NumberOfUppercaseLetters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");
        String str = sc.nextLine();

        System.out.println(count(str, str.length() - 1));
    }

    public static int count(String str, int idx) {
        if (idx < 0) {
            return 0;
        }

        if (Character.isUpperCase(str.charAt(idx))) {
            return 1 + count(str, idx - 1);
        }

        return count(str, idx - 1);
    }
}
