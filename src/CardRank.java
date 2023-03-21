public enum CardRank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    public static CardRank cardRankPick(int j){
        CardRank[] rank = values();
        return rank[j];
    }
}
