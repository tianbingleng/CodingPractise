package DeckOfCard;

/**
 * Created by tianbingleng on 22/10/2017.
 */
public class Card {
    private int faceValue; // 1 for A, 11 for J, 12Q, 13K
    private Suit suit;

    public Card(int c, Suit s) {
        faceValue = c;
        suit = s;
    }

    public int value() {
        return faceValue;
    }

    public Suit suit() {
        return suit;
    }

    public void print() {
        String[] faceValues = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        System.out.print(faceValues[faceValue - 1]);
        switch (suit) {
            case Club:
                System.out.print("C");
                break;
            case Heart:
                System.out.print("H");
                break;
            case Diamond:
                System.out.print("D");
                break;
            case Spade:
                System.out.print("S");
                break;
        }
        System.out.print(" ");
    }
}
