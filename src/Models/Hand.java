package Models;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }


    public void addCards(Card card){
        this.cards.add(card);
    }
    public void  playCard(Card card){
        cards.remove(card);

    }
    public int size(){
        return cards.size();
    }
    public boolean hasCard(Card card){
        for(Card c:cards){
            if(c.getSuit()==card.getSuit()&&c.getRank()==card.getRank()){
                return true;
            }
        }
        return false;
    }


}
