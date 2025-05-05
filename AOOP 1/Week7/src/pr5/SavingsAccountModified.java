package pr5;

import pr4.SavingsAccount;

public class SavingsAccountModified {
    private static double mAnnualInterestRate;
    private double mSavingsBalance;
    private static int mNumber;

    public SavingsAccountModified(double mSavingsBalance) {
        modifyInterestRate(2);
        setmSavingsBalance(mSavingsBalance);
        mNumber++;
    }

    public SavingsAccountModified() {
        this(0);
    }

    public SavingsAccountModified(SavingsAccount savingsAccount) {
        this(savingsAccount.getmSavingsBalance());
    }

    public static double getmAnnualInterestRate() {
        return mAnnualInterestRate;
    }

    public static void modifyInterestRate(double mAnnualInterestRate) {
        SavingsAccountModified.mAnnualInterestRate = mAnnualInterestRate;
    }

    public double getmSavingsBalance() {
        return mSavingsBalance;
    }

    public void setmSavingsBalance(double mSavingsBalance) {
        this.mSavingsBalance = mSavingsBalance;
    }

    public static int getmNumber() {
        return mNumber;
    }

    public void calculateMonthlyInterest() {
        mSavingsBalance += (mSavingsBalance * mAnnualInterestRate) / 12.0;
    }

    public boolean isGreater(SavingsAccount acc) {
        return mSavingsBalance > acc.getmSavingsBalance();
    }

//    A way of keeping track when GC is called
//    @Override
//    protected void finalize() throws Throwable {
//        mNumber--;
//    }

    @Override
    public String toString() {
        return String.format("Interest rate: %s, Balance: %s%n", mAnnualInterestRate, mSavingsBalance);
    }
}