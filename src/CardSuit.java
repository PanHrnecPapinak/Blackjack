public enum CardSuit {
    SPADES, CLUBS, DIAMONDS, HEARTS;

    public static CardSuit cardSuitPick(int i){
        CardSuit[] suit = values();
        return suit[i];
    }
}
