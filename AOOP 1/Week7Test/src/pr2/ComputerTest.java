package pr2;

import java.util.Arrays;

public class ComputerTest {
    //Computer library needs deep copy
    public static void main(String[] args) {
        Computer computer1 = new Computer("gaming", 5.3, new String[]{"games.txt", "songs.txt"});

        System.out.printf("Computer1: %s%n", computer1);

        Computer computer2 = computer1;

        System.out.printf("Computer2 after using Copy Constructor: %s%n", computer2);

        Computer computer3 = new Computer();

        System.out.printf("Computer3: %s%n", computer3);

        computer2 = computer3;

        computer1.setType("home");

        System.out.printf("Computer2 after using Copy Constructor: %s%n", computer2);
        System.out.printf("Computer3: %s%n", computer3);

        System.out.printf("Computer1 after setters: %s%nFiles only: %s", computer1, Arrays.toString(computer1.getFiles()));
    }
}
