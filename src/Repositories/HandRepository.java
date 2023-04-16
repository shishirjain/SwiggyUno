package Repositories;

import Models.Card;
import Models.Hand;

import java.util.List;

public class HandRepository {
    private final CardRepository cardRepository;

    public HandRepository() {
        cardRepository = new CardRepository();
    }

    public void addCards(List<Card> cards) {
        cardRepository.addCards(cards);
    }

    public Card playCard(int index) {
        return cardRepository.removeCard(index);
    }

    public boolean contains(Card card) {
        return cardRepository.getCards().contains(card);
    }

    public int getSize() {
        return cardRepository.getSize();
    }

    public boolean isEmpty() {
        return cardRepository.isEmpty();
    }

    public List<Card> getCards() {
        return cardRepository.getCards();
    }

    public void clear() {
        cardRepository.clear();
    }

    public Hand createHand() {
        return new Hand();
    }
}
