package Models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Deck deck;
    private final Hand discardPile;
    private int currentPlayerIndex;
    private int direction;
    private int penaltyCards;
    public Game(List<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        deck = new Deck();
        discardPile = new Hand();
        currentPlayerIndex = 0;
        direction = 1;
        penaltyCards = 0;
        dealCards();
        discardPile.addCards(deck.drawCard());
    }

    private void dealCards() {
        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                Card card = deck.drawCard();
                if (card != null) {
                    player.getHand().addCards(card);
                }
            }
        }
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
    }

    private void applyPenalty() {
        for (int i = 0; i < penaltyCards; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                players.get(currentPlayerIndex).getHand().addCards(card);
            }
        }
        penaltyCards = 0;
    }

    public boolean playCard(Card card) {
        if (card == null) {
            return false;
        }
        if (!players.get(currentPlayerIndex).getHand().hasCard(card)) {
            return false;
        }
        if (penaltyCards > 0 && card.getRank() != Rank.Jack) {
            return false;
        }
        Card topCard = discardPile.getCard(discardPile.getSize() - 1);
        if (card.getSuit() == topCard.getSuit() || card.getRank() == topCard.getRank()) {
            discardPile.addCard(players.get(currentPlayerIndex).getHand().playCard(
                    players.get(currentPlayerIndex).getHand().getCards().indexOf(card)));
            if (card.getRank() == Rank.ACE) {
                nextPlayer();
            } else if (card.getRank() == Rank.KING) {
                direction = -direction;
                nextPlayer();
            } else if (card.getRank() == Rank.QUEEN) {
                penaltyCards += 2;
                nextPlayer();
            } else if (card.getRank() == Rank.JACK) {
                penaltyCards += 4;
            } else {
                nextPlayer();
            }
            if (players.get(currentPlayerIndex).getHand().isEmpty()) {
                return true;
            }
            applyPenalty();
            return true;
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public int getDirection() {
        return direction;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Hand getDiscardPile() {
        return discardPile;
    }

}
