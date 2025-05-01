package pr4;

import java.util.Arrays;
import java.util.Scanner;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter list 1: ");
        int list1Len = sc.nextInt();

        int[] list1 = new int[list1Len];

        for (int i = 0; i < list1Len; i++) {
            list1[i] = sc.nextInt();
        }

        System.out.print("Enter list 2: ");
        int list2Len = sc.nextInt();

        int[] list2 = new int[list2Len];

        for (int i = 0; i < list2Len; i++) {
            list2[i] = sc.nextInt();
        }

        System.out.println("The merged list is: " + Arrays.toString(merge(list1, list2)));
    }

    public static int[] merge(int[] list1, int[] list2) {
        int[] result = new int[list1.length + list2.length];
        int i = 0, j = 0, k = 0;

        while (i < list1.length && j < list2.length) {
            if (list1[i] <= list2[j]) {
                result[k++] = list1[i++];
            } else {
                result[k++] = list2[j++];
            }
        }

        while (i < list1.length) {
            result[k++] = list1[i++];
        }

        while (j < list2.length) {
            result[k++] = list2[j++];
        }

        return result;
    }
}
