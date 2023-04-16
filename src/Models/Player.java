package Models;

import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name, List<Card> hand) {
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }
    public void addcard(Card card){
        hand.add(card);
    }
    public Card playCard(int index) {
        return hand.remove(index);
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }
}
