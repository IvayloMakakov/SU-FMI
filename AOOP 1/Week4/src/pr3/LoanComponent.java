package pr3;

public class LoanComponent {
    private double annualInterestRate;
    private double numberOfYears;
    private double loanAmount;

    public LoanComponent(double annualInterestRate, double numberOfYears, double loanAmount) {
        setAnnualInterestRate(annualInterestRate);
        setNumberOfYears(numberOfYears);
        setLoanAmount(loanAmount);
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public double getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(double numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getMonthlyPayment() {
        double r = (annualInterestRate / 100.0) / 12,
                n = numberOfYears * 12, curr = Math.pow(1 + r, n);

        return loanAmount * r * curr / (curr - 1.0);
    }

    public double getTotalAmount() {
        return getMonthlyPayment() * 12 * numberOfYears;
    }
}
