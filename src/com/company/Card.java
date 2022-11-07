package com.company;

import java.util.ArrayList;

public class Card implements Comparable<Card> {

    private Suits suit;
    private Values value;
    private boolean inRank;

    public Card() {}



    public Card(Suits suit,Values value) {
        this.suit = suit;
        this.value = value;
        this.inRank = false;
    }

    public Suits getSuit() {
        return suit;
    }

    public Values getValue() {
        return value;
    }

    @Override
    public int compareTo(Card o) {
        return this.getValue().getCardValue() - o.getValue().getCardValue();
    }


    public enum Suits {
        SPADES, HEARTS, DIAMONDS, CLUBS,
    }



    public enum Values {
        DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
        EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

        private  int value;

        Values(int value) {
            this.value = value;
        }

        public int getCardValue() {
            return value;
        }
    }

    public void setSuit(Suits suit) {
        this.suit = suit;
    }

    public void setValue(Values value) {
        this.value = value;
    }

    public ArrayList<Card> generateDeck() {
        ArrayList<Card> deck = new ArrayList<>();
        for(Suits suit : Suits.values()) {
            for(Values value: Values.values()) {
                deck.add(new Card(suit,value));
            }
        }

        return deck;
    }

    @Override
    public String toString() {
        return "{" +
                "suit=" + suit +
                ", value=" + value +
                '}' + "\n";
    }
}
