package Repositories;

import Models.Card;

import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    private final List<Card> cards;


    public CardRepository() {
        cards = new ArrayList<>();
    }
    public void addCard(Card card) {
        cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public Card getCard(int index) {
        if (index < 0 || index >= cards.size()) {
            return null;
        }
        return cards.get(index);
    }

    public Card removeCard(int index) {
        if (index < 0 || index >= cards.size()) {
            return null;
        }
        return cards.remove(index);
    }

    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    public int getSize() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void clear() {
        cards.clear();
    }
}
