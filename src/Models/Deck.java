package Models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Deck {
    private List<Card> cards;

    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Spades", "Clubs"};
        int[] ranks = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        cards = new ArrayList<>();

        for (String suit : suits) {
            for (int rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}