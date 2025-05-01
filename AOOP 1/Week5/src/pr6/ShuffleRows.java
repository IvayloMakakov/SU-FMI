package pr6;

import java.util.Arrays;
import java.util.Random;

public class ShuffleRows {
    public static void main(String[] args) {
        int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};

        shuffle(m);

        for (int i = 0; i < m.length; i++) {
            System.out.print(Arrays.toString(m[i]) + " ");
        }
    }

    public static void shuffle(int[][] m) {
        Random r = new Random();
        int howManyShuffles = r.nextInt(9999);

        for (int i = 0; i < howManyShuffles; i++) {
            int firstIndex = r.nextInt(m.length);
            int secondIndex = r.nextInt(m.length);

            int[] firstRow = m[firstIndex];
            int[] secondRow = m[secondIndex];
            m[firstIndex] = m[secondIndex];
            m[secondIndex] = firstRow;
        }
    }
}
