package pr1c;

import pr1.Card;
import pr1.DeckOfCards;

public class PickFourTest {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();

        Card[] cards = new Card[4];

        int counter = 0, sum = 0;

        boolean flag = true;

        while (flag) {
            deck.shuffle();
            sum = 0;
            for (int i = 0; i < 4; i++) {
                cards[i] = deck.dealCard();
                sum += cards[i].getValue();
            }

            counter++;

            if (sum == 24) {
                flag = false;

                System.out.println("Count of picks: " + counter);
            }
        }
    }
}