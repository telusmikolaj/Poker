package com.company;

public class Player {

    private String name;
    private Hand hand;
    private int amountOfTokens;
    private int currentBet;
    private Deck deck;

    public Player(String name, Deck deck) {
        this.name = name;
        this.deck = deck;
        this.hand = new Hand(deck);
        this.amountOfTokens = 1000;
        this.currentBet = 0;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public void setAmountOfTokens(int amountOfTokens) {
        this.amountOfTokens = amountOfTokens;
    }

    public void reshuffle() {
        this.hand = new Hand(deck);
    }

    public void exchangeCard(int index) {
        this.hand.exchangeCard(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hand getHand() {

        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public int getAmountOfTokens() {
        return amountOfTokens;
    }
}
