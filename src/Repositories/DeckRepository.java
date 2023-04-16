package Repositories;

import Models.Card;
import Models.Deck;

import java.util.List;

public class DeckRepository {
    private final CardRepository cardRepository;

    public DeckRepository() {
        cardRepository = new CardRepository();
    }

    public void addCards(List<Card> cards) {
        cardRepository.addCards(cards);
    }

    public Card drawCard() {
        return cardRepository.removeCard(cardRepository.getSize() - 1);
    }

    public boolean isEmpty() {
        return cardRepository.isEmpty();
    }

    public void shuffle() {
        for (int i = 0; i < cardRepository.getSize(); i++) {
            int randomIndex = (int) (Math.random() * cardRepository.getSize());
            Card temp = cardRepository.getCard(i);
            cardRepository.getCard(i).setCard(cardRepository.getCard(randomIndex));
            cardRepository.getCard(randomIndex).setCard(temp);
        }
    }

    public Deck createDeck() {
        return new Deck();
    }
}
