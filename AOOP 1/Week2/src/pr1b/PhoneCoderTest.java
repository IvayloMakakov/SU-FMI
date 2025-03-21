package pr1b;

import java.util.Scanner;

public class PhoneCoderTest {
    public static void main(String[] args) {
        PhoneCoder pc = new PhoneCoder();

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter phone number: ");
        int number = sc.nextInt();

        //допускам, че потребителя ще въвежда правилен четирицифрен номер, като добавям случай за начална или само 0

        pc.setKey(5);

        String enc = pc.encode(String.format("%s", number));

        System.out.println(pc.toString());
        System.out.println("Encoded: " + enc);
        System.out.println("Decoded: " + pc.decode(enc));
    }
}
