package pr4;

public class SavingsAccount {
    private static double mAnnualInterestRate;
    private double mSavingsBalance;
    private static int mNumber;

    public SavingsAccount(double mSavingsBalance) {
        modifyInterestRate(2);
        setmSavingsBalance(mSavingsBalance);
        mNumber++;
    }

    public SavingsAccount() {
        this(0);
    }

    public SavingsAccount(SavingsAccount savingsAccount) {
        this(savingsAccount.getmSavingsBalance());
    }

    public static double getmAnnualInterestRate() {
        return mAnnualInterestRate;
    }

    public static void modifyInterestRate(double mAnnualInterestRate) {
        SavingsAccount.mAnnualInterestRate = mAnnualInterestRate;
    }

    public double getmSavingsBalance() {
        return mSavingsBalance;
    }

    public void setmSavingsBalance(double mSavingsBalance) {
        this.mSavingsBalance = mSavingsBalance;
    }

    public void calculateMonthlyInterest() {
        mSavingsBalance += (mSavingsBalance * mAnnualInterestRate) / 12.0;
    }

    public boolean isGreater(SavingsAccount acc) {
        return mSavingsBalance > acc.getmSavingsBalance();
    }

    @Override
    public String toString() {
        return String.format("Interest rate: %s, Balance: %s%n", mAnnualInterestRate, mSavingsBalance);
    }
}
