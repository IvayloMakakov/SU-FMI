package pr2;

import java.util.Scanner;

public class ComputeByMenu {
    private double x;

    public ComputeByMenu(double x) {
        this.x = x;
    }

    public void displayMenu() {
        for (int i = 0; i < 60; i++) {
            System.out.println();
        }

        System.out.printf("""
                1. Update the floating point number
                2. Compute and display exp(x)
                3. Compute and display sin(x)
                4. Compute and display floor(x)
                5. Exit
                """);
    }

    public void doSelection(int choice) {
        switch (choice) {
            case 1:
                Scanner input = new Scanner(System.in);
                System.out.print("Enter double number: ");
                x = input.nextDouble();
                break;
            case 2:
                System.out.printf("Exp(%f) = %f", x, Math.exp(x));
                break;
            case 3:
                System.out.printf("Sin(%f) = %f", x, Math.sin(Math.toRadians(x)));
                break;
            case 4:
                System.out.printf("Floor(%f) = %f", x, Math.floor(x));
                break;
            case 5:
                System.exit(0);
        }
    }

    public void getUserChoice() {
        Scanner input = new Scanner(System.in);
        while (true) {
            displayMenu();
            System.out.print("Enter choice: ");
            int choice = input.nextInt();

            while (choice < 1 || choice > 6) {
                System.out.println("Invalid choice");
                System.out.print("Enter choice: ");
                choice = input.nextInt();
            }

            doSelection(choice);
        }
    }
}
