package com.company;

import java.util.ArrayList;

public class Deck {

    private ArrayList<Card> deck;
    private Card card;
    private int size = 52;

    public Deck() {
        card = new Card();
        deck = card.generateDeck();
    }

    @Override
    public String toString() {
        return "Deck{ \n" + deck + "\n"
                 +'}';
    }

    public int getSize() {
        return this.deck.size();
    }

    public void setSize(int size) {
        this.size = size;
    }

    public  Card getCardFromDeck(int i) {
        Card card  = deck.get(i);
        deck.remove(i);
        return card;
    }
}
