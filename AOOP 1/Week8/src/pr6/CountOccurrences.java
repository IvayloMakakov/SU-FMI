package pr6;

import java.util.Scanner;

public class CountOccurrences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");
        String str = sc.nextLine();

        System.out.print("Enter char: ");
        char ch = sc.next().charAt(0);

        System.out.println(count(str, ch));
    }

    public static int count(String str, char a) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        if (str.charAt(str.length() - 1) == a) {
            return 1 + count((str.substring(0, str.length() - 1)), a);
        }

        return count((str.substring(0, str.length() - 1)), a);
    }
}
