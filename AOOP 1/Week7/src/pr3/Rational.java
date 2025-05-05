package pr3;

public class Rational {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("denominator is zero");
        }

        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }

        int gcd = GCD(numerator, denominator);

        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public Rational() {
        this(1, 1);
    }

    public Rational(Rational rational) {
        this(rational.getNumerator(), rational.getDenominator());
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;

        simplify();
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("denominator is zero");
        }

        if (denominator < 0) {
            denominator = -denominator;
            numerator = -numerator;
        }

        this.denominator = denominator;

        simplify();
    }

    //chose recursion
    private int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }

    private void simplify() {
        int gcd = GCD(numerator, denominator);

        numerator /= gcd;
        denominator /= gcd;
    }

    public void add(Rational rational) {
        this.numerator *= rational.getDenominator();
        this.numerator += this.denominator * rational.getNumerator();

        this.denominator *= rational.getDenominator();

        simplify();
    }

    public void subtract(Rational rational) {
        this.numerator *= rational.getDenominator();
        this.numerator -= this.denominator * rational.getNumerator();

        this.denominator *= rational.getDenominator();

        simplify();
    }

    public void multiply(Rational rational) {
        this.numerator *= rational.getNumerator();
        this.denominator *= rational.getDenominator();

        simplify();
    }

    public void divide(Rational rational) {
        this.numerator *= rational.getDenominator();
        this.denominator *= rational.getNumerator();

        simplify();
    }

    @Override
    public String toString() {
        return String.format("%d / %d", numerator, denominator);
    }
}