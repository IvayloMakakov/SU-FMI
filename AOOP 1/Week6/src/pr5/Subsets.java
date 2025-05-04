package pr5;

import java.util.ArrayList;
import java.util.Scanner;

public class Subsets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter k: ");

        int k = sc.nextInt();

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.println(generateSubsets(k, n).toString());
    }

    public static ArrayList<ArrayList<Integer>> generateSubsets(int k, int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        generateSubsetsHelper(1, n, k, new ArrayList<Integer>(), result);

        return result;
    }

    public static void generateSubsetsHelper(int start, int n, int k, ArrayList<Integer> current, ArrayList<ArrayList<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i);
            generateSubsetsHelper(i + 1, n, k, current, result);
            current.remove(current.size() - 1);
        }
    }
}