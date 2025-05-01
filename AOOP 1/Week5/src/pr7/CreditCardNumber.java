package pr7;

import java.util.Scanner;

public class CreditCardNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a credit card number as a long integer: ");

        long cardNumber = sc.nextLong();

        System.out.println(checkCreditCardNumber(cardNumber));
    }

    public static String checkCreditCardNumber(long cardNumber) {
        String creditCardNumber = String.valueOf(cardNumber);
        char[] creditCardNumberCharArray = creditCardNumber.toCharArray();

        if (creditCardNumber.length() < 13 || creditCardNumber.length() > 16 || (creditCardNumberCharArray[0] != '4'
                && creditCardNumberCharArray[0] != '5' && creditCardNumberCharArray[0] != '6'
                && (creditCardNumberCharArray[0] != '3' && creditCardNumberCharArray[1] != '7'))) {
            return creditCardNumber + " is invalid";
        }

        int sumStep1 = 0;
        int sumStep2 = 0;

        for (int i = 0; i < creditCardNumberCharArray.length; i++) {
            if (i % 2 == 0) {
                sumStep1 += sumToOneNumber((creditCardNumberCharArray[i] - '0') * 2, 0);
            } else {
                sumStep2 += creditCardNumberCharArray[i] - '0';
            }
        }

        return (sumStep1 + sumStep2) % 10 == 0 ? creditCardNumber + " is valid" : creditCardNumber + " is invalid";
    }

    private static int sumToOneNumber(int num, int sum) {
        if (num <= 0) {
            if (sum < 10) {
                return sum;
            }

            return sumToOneNumber(sum, 0);
        }

        return sumToOneNumber(num / 10, sum + num % 10);
    }
}
