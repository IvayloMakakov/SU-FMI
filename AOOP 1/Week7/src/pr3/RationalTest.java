package pr3;

public class RationalTest {
    public static void main(String[] args) {
        Rational rational = new Rational(3, 4);

        System.out.println(rational);

        rational.add(new Rational(1, 2));

        System.out.println(rational);

        rational.subtract(new Rational(1, 2));

        System.out.println(rational);

        rational.add(new Rational(71, 24));

        System.out.println(rational);

        rational.multiply(new Rational(1, 2));

        System.out.println(rational);

        rational.divide(new Rational(1, 3));

        System.out.println(rational);

        rational.subtract(new Rational(79, 16));

        System.out.println(rational);

        rational.multiply(new Rational(-5, 2));

        System.out.println(rational);
    }
}