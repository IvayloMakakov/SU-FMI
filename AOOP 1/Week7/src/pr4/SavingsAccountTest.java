package pr4;

import java.util.Random;

public class SavingsAccountTest {
    public static void main(String[] args) {
        SavingsAccount acc = new SavingsAccount(10_000);
        SavingsAccount acc2 = new SavingsAccount(new Random().nextInt(100, 10_000));

        SavingsAccount.modifyInterestRate(6);

        System.out.println(acc.isGreater(acc2));

        SavingsAccount acc3 = acc2;

        System.out.println(acc3.isGreater(acc2));

        System.out.println(acc);
        System.out.println(acc2);
        System.out.println(acc3);
    }
}