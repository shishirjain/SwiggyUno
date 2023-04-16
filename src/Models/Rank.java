package Models;

public enum Rank {
    Ace("A"),Two("2"),Three("3"),Four("4") ,Five("5"),Six("6"),Seven("7")
    ,Eight("8"),Nine("9"),Ten("10"),Jack("J"),Q("Q"),K("K");
    private final String symbol;
    Rank(String symbol){
        this.symbol=symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
