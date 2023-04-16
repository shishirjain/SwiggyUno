package Models;

public enum Suit {
    Heart("♥️"),Club("♣️"),Spade("♠️"),Diamond("♦️");
    private final String symbol;
    Suit(String symbol){
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
