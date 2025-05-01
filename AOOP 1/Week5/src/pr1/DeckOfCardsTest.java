// Fig. 7.11: DeckOfCardsTest.java
// Card shuffling and dealing application.
package pr1;

public class DeckOfCardsTest {
    // execute application
    public static void main(String args[]) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle(); // place Cards in random order

        Card[] leftHand = myDeckOfCards.dealHand();
        Card[] rightHand = myDeckOfCards.dealHand();

        System.out.printf("%-20s%-20s\n",
                "Left hand:", "Right hand:");

        for (int i = 0; i < leftHand.length; i++)//or right
        {
            System.out.printf("%-20s%-20s\n",
                    leftHand[i], rightHand[i]);
        } // end for

        System.out.printf("%n%-20s\n", "Hand values:");

        int leftHandStrengthCode = myDeckOfCards.handStrengthCode(leftHand);
        int rightHandStrengthCode = myDeckOfCards.handStrengthCode(rightHand);

        System.out.printf("%-20s%-20s\n",
                myDeckOfCards.handStrengthText(leftHandStrengthCode), myDeckOfCards.handStrengthText(rightHandStrengthCode));

        if (leftHandStrengthCode > rightHandStrengthCode) {
            System.out.println("\nResult: left hand is better");
        } else if (leftHandStrengthCode < rightHandStrengthCode) {
            System.out.println("\nResult: right hand is better");
        } else {
            System.out.println("\nResult: left hand and right hand are equal");
        }
    } // end main
} // end class DeckOfCardsTest


