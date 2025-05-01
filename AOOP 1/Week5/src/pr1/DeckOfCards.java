// Fig. 7.10: DeckOfCards.java
// DeckOfCards class represents a deck of playing cards.
package pr1;

import java.util.Random;

public class DeckOfCards {
    private Card deck[]; // array of Card objects
    private int currentCard; // index of next Card to be dealt
    private final int NUMBER_OF_CARDS = 52; // constant number of Cards
    private Random randomNumbers; // random number generator
    private final String FACES[] = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    // constructor fills deck of Cards
    public DeckOfCards() {
        String suits[] = {"Hearts", "Diamonds", "Clubs", "Spades"};

        deck = new Card[NUMBER_OF_CARDS]; // create array of Card objects
        currentCard = 0; // set currentCard so first Card dealt is deck[ 0 ]
        randomNumbers = new Random(); // create random number generator

        // populate deck with Card objects
        for (int count = 0; count < deck.length; count++)
            deck[count] =
                    new Card(FACES[count % 13], suits[count / 13]);
    } // end DeckOfCards constructor

    // shuffle deck of Cards with one-pass algorithm
    public void shuffle() {
        // after shuffling, dealing should start at deck[ 0 ] again
        currentCard = 0; // reinitialize currentCard

        // for each Card, pick another random Card and swap them
        for (int first = 0; first < deck.length; first++) {
            // select a random number between 0 and 51
            int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

            // swap current Card with randomly selected Card
            Card temp = deck[first];
            deck[first] = deck[second];
            deck[second] = temp;
        } // end for
    } // end method shuffle

    // deal one Card
    public Card dealCard() {
        // determine whether Cards remain to be dealt
        if (currentCard < deck.length)
            return deck[currentCard++]; // return current Card in array
        else
            return null; // return null to indicate that all Cards were dealt
    } // end method dealCard

    public Card[] dealHand() {
        Card[] hand = new Card[5];

        for (int i = 0; i < hand.length; i++) {
            hand[i] = dealCard();
        }

        return hand;
    }

    private int[] countFaces(Card[] hand) {
        int[] counts = new int[FACES.length];

        for (int i = 0; i < hand.length; i++) {
            for (int j = 0; j < FACES.length; j++) {
                if (hand[i].getFace().equals(FACES[j])) {
                    counts[j]++;
                }
            }
        }

        return counts;
    }

    private int countTwos(int[] counts) {
        int count = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == 2) {
                count++;
            }
        }
        return count;
    }

    private boolean countThrees(int[] countFaces) {
        for (int i = 0; i < countFaces.length; i++) {
            if (countFaces[i] == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPair(Card[] hand) {
        int[] countFaces = countFaces(hand);
        return countTwos(countFaces) == 1 && !countThrees(countFaces);
    }

    public boolean hasTwoPairs(Card[] hand) {
        return countTwos(countFaces(hand)) == 2;
    }

    public boolean hasThreeOfAKind(Card[] hand) {
        int[] countFaces = countFaces(hand);
        return countTwos(countFaces) == 0 && countThrees(countFaces);
    }

    public boolean hasFourOfAKind(Card[] hand) {
        int[] countFaces = countFaces(hand);
        for (int i = 0; i < countFaces.length; i++) {
            if (countFaces[i] == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFlush(Card[] hand) {
        String handSuit = hand[0].getSuit();
        for (int i = 1; i < hand.length; i++) {
            if (!hand[i].getSuit().equals(handSuit)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasStraightFlush(Card[] hand) {
        int[] countFaces = countFaces(hand);
        int countOnes = 0;
        for (int i = 0; i < countFaces.length; i++) {
            if (countFaces[i] == 1) {
                countOnes++;
            } else {
                countOnes = 0;
            }
            if (countOnes == 5) {
                return true;
            }
        }
        return countOnes == 4 && countFaces[0] == 1;
    }

    public boolean hasFullHouse(Card[] hand) {
        int[] countFaces = countFaces(hand);
        return countTwos(countFaces) == 1 && countThrees(countFaces);
    }

    public int handStrengthCode(Card[] hand) {
        if (hasStraightFlush(hand) && hasFlush(hand)) {
            return 8;
        }
        if (hasFourOfAKind(hand)) {
            return 7;
        }
        if (hasFullHouse(hand)) {
            return 6;
        }
        if (hasFlush(hand)) {
            return 5;
        }
        if (hasStraightFlush(hand)) {
            return 4;
        }
        if (hasThreeOfAKind(hand)) {
            return 3;
        }
        if (hasTwoPairs(hand)) {
            return 2;
        }
        if (hasPair(hand)) {
            return 1;
        }
        return 0;
    }

    public String handStrengthText(int handStrengthCode) {
        String[] strengths = {"None", "Pair", "Two pairs", "Three of a kind", "Straight", "Flush", "Full house", "Four of a kind", "Straight Flush"};

        return strengths[handStrengthCode];
    }
} // end class DeckOfCards


