package pr5b;

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string: ");
        String str = sc.nextLine();

        reverseDisplay(str,str.length()-1);
    }

    public static void reverseDisplay(String value, int high){
        if (high<0){
            return;
        }

        System.out.print(value.charAt(high));
        reverseDisplay(value,high-1);
    }
}
