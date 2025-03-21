package pr5a;

public class E {
    public static void main(String[] args) {
        System.out.printf("%.3f", getCloseValueOfE());
    }

    public static double getCloseValueOfE() {
        double sum = 0.0;
        int fact = 1;

        for (int i = 1; i <= 20; i++)//fixed 20 iterations
        {
            sum += 1.0 / fact;
            fact *= i;
        }

        return sum;
    }

    //Без UML диаграма за дейност
}
