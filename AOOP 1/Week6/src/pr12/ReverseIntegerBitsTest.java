package pr12;

import java.util.Scanner;

public class ReverseIntegerBitsTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an integer to reverse: ");
        int n = sc.nextInt();

        System.out.println("Before reversing " + n);
        System.out.println("Before reversing " + toBinaryString(n));

        int n1 = reverseBits(n);

        System.out.println("After reversing " + n1);
        System.out.println("After reversing " + toBinaryString(n1));

        int n2 = reverseBitsRecursive(n);

        System.out.println("After reversing " + n2);
        System.out.println("After reversing " + toBinaryString(n2));
    }

    public static String toBinaryString(int value) {
        return String.format("%32s", Integer.toBinaryString(value)).replace(' ', '0');
    }

    public static int reverseBits(int n) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= (n & 1);
            n >>>= 1;
        }

        return result;
    }

    public static int reverseBitsRecursive(int n) {
        return reverseBitsRecursiveHelper(n, 0, 32);
    }


    public static int reverseBitsRecursiveHelper(int n, int result, int bitsLeft) {
        if (bitsLeft == 0) {
            return result;
        }

        result <<= 1;
        result |= (n & 1);
        return reverseBitsRecursiveHelper(n >> 1, result, bitsLeft - 1);
    }
}
