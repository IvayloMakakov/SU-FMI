package pr9;

public class ComputeSeries {
    public static void main(String[] args) {
        for (int i = 10; i >= 1; i--) {
            System.out.println(computeSeries(i));
        }
    }

    public static double computeSeries(int i) {
        if (i == 0) {
            return 0;
        }

        return i / (2.0 * i + 1) + computeSeries(i - 1);
    }
}
