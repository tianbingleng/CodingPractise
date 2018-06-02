package DeckOfCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by tianbingleng on 22/10/2017.
 */
public class Hand {
    protected final List<Card> cards = new ArrayList<>();

    public int score() {
        int score = 0;
        for (Card card : cards) {
            score += card.value();
        }
        return score;
    }

    public void addCards(Card[] c) {
        Collections.addAll(cards, c);
    }

    public int size() {
        return cards.size();
    }

    public void print() {
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).print();
        }
    }
}
