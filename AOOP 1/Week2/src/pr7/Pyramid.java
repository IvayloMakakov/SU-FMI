package pr7;

public class Pyramid {
    public static void main(String[] args) {
        printPyramidPattern1();

        printPyramidPattern2();

        printPyramidPattern3();

        printPyramidPattern4();

        System.out.println();

        printPyramidPattern5();

        printPyramidPattern6();
    }

    public static void printPyramidPattern1() {
        for (int i = 1; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void printPyramidPattern2() {
        for (int i = 1; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void printPyramidPattern3() {
        for (int i = 1; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    public static void printPyramidPattern4() {
        for (int i = 0; i <= 9; i++) {
            for (int j = i; j <= 9; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            for (int j = i - 1; j >= 1; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void printPyramidPattern5() {
        for (int i = 9; i >= 0; i--) {
            for (int j = i; j <= 9; j++) {
                System.out.print("  ");
            }
            for (int j = 1; j < i; j++) {
                System.out.print(j + " ");
            }
            for (int j = i; j >= 1; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public static void printPyramidPattern6() {
        for (int i = 0; i < 9; i++) {
            for (int j = i; j <= 9; j++) {
                System.out.print("  ");
            }
            for (int j = 9 - i; j <= 9; j++) {
                System.out.print(j + " ");
            }
            for (int j = 8; j >= 9 - i; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
