package Models;

public class Card {
    private int rank;
    private Character suit;

    public Card(int rank, Character suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public Character getSuit() {
        return suit;
    }
    public String toString(){
        return rank+" "+suit;
    }
}
