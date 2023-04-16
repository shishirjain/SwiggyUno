package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards=new ArrayList<>();
        for(Suit suit:Suit.values()){
            for(Rank rank:Rank.values()){
                Card card=new Card(rank,suit) ;
                this.cards.add(card);
            }
        }
        Collections.shuffle(cards);
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
    public Card drawCard(){
        return cards.remove(cards.size()-1);
    }
    public int size(){
        return cards.size();
    }
}
