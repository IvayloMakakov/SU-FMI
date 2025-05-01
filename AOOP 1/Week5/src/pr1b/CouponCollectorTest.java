package pr1b;

import pr1.Card;
import pr1.DeckOfCards;

public class CouponCollectorTest {
    public static void main(String[] args) {
        DeckOfCards deck = new DeckOfCards();

        deck.shuffle();

        boolean[] countSuits = new boolean[4];//"Hearts", "Diamonds", "Clubs", "Spades"
        String[] cards = new String[4];
        int counter = 0;

        while (!countSuits[0] || !countSuits[1] || !countSuits[2] || !countSuits[3]) {
            Card card = deck.dealCard();

            if (card.getSuit().equals("Hearts")) {
                countSuits[0] = true;
                cards[0] = card.toString();
            } else if (card.getSuit().equals("Diamonds")) {
                countSuits[1] = true;
                cards[1] = card.toString();
            } else if (card.getSuit().equals("Clubs")) {
                countSuits[2] = true;
                cards[2] = card.toString();
            } else if (card.getSuit().equals("Spades")) {
                countSuits[3] = true;
                cards[3] = card.toString();
            }

            counter++;
        }

        for (var card : cards) {
            System.out.println(card);
        }

        System.out.println("Number of picks: " + counter);
    }
}
