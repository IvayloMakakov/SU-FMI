package pr5;

import java.util.Scanner;

public class MultiplyTwoMatrices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter two 3x3 matrices");

        System.out.print("Enter matrix1: ");

        double[][] matrix1 = new double[3][3];

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                matrix1[i][j] = sc.nextDouble();
            }
        }

        System.out.print("Enter matrix2: ");

        double[][] matrix2 = new double[3][3];

        for (int i = 0; i < matrix2.length; i++) {
            for (int j = 0; j < matrix2[i].length; j++) {
                matrix2[i][j] = sc.nextDouble();
            }
        }

        double[][] resultMatrix = multiplyMatrix(matrix1, matrix2);

        printMatrixes(matrix1, matrix2, resultMatrix);
    }

    public static double[][] multiplyMatrix(double[][] a, double[][] b) {
        double[][] resultMatrix = new double[a.length][a[0].length];

        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = 0; k < a.length; k++) {
                    resultMatrix[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return resultMatrix;
    }

    private static void printMatrixes(double[][] matrix1, double[][] matrix2, double[][] resultMatrix) {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                System.out.print(matrix1[i][j] + " ");
            }

            if ((i - 1) % 2 == 0) {
                System.out.print("  *   ");
            } else {
                System.out.print("      ");
            }

            for (int j = 0; j < matrix2[i].length; j++) {
                System.out.print(matrix2[i][j] + " ");
            }

            if ((i - 1) % 2 == 0) {
                System.out.print("  =   ");
            } else {
                System.out.print("      ");
            }

            for (int j = 0; j < matrix2[i].length; j++) {
                System.out.printf("%.1f ", resultMatrix[i][j]);
            }

            System.out.println();
        }
    }
}
