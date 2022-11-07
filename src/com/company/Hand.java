package com.company;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand {

    private Card[] hand;
    private int handSize = 5;
    private int size;
    private int highValue;
    private int secondValue;
    private int thridValue;
    private int fourthValue;
    private int fifthValue;
    private Deck deck;
    public HandRankings handRankings;

    public int getHandRankings() {
        return handRankings.getRank();
    }



    public void setHandRankings(HandRankings handRankings) {
        this.handRankings = handRankings;
    }

    public Hand(Deck deck) {
        this.hand = new Card[5];
        this.highValue = 0;
        this.handRankings = HandRankings.NONE;
        this.deck = deck;
        size = hand.length;
        for (int i = 0; i < hand.length; i++) {
            int random = getRandomNumber(0,deck.getSize());
            hand[i] = deck.getCardFromDeck(random);
        }
        Arrays.sort(hand);
    }


    public enum HandRankings {
        NONE(-1) ,HIGHCARD(0), ONEPAIR(1), TWOPAIRS(2), TRIPS(3), STRAIGHT(4),
        FLUSH(5), FULLHOUSE(6), QUADS(7), STRAIGHTFLUSH(8);

        private  int value;

        HandRankings(int value) {
            this.value = value;
        }

        public int getRank() {
            return value;
        }
    }



    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public void exchangeCard(int index) {
        int random = getRandomNumber(0,deck.getSize());
        hand[index] = this.deck.getCardFromDeck(random);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHighValue() {
        return highValue;
    }

    public void setHighValue(int highValue) {
        this.highValue = highValue;
    }

    public void setCardsValues() {
        Arrays.sort(this.hand);
        this.highValue = this.hand[4].getValue().getCardValue();
        this.secondValue = this.hand[3].getValue().getCardValue();
        this.thridValue = this.hand[2].getValue().getCardValue();
        this.fourthValue = this.hand[1].getValue().getCardValue();
        this.fifthValue = this.hand[0].getValue().getCardValue();

    }

    public void printHand() {
        for(int i =0; i < hand.length;i++) {
            System.out.print(hand[i]);
        }
        System.out.println();
    }

    public Card[] getHand() {
        return hand;
    }

//    public boolean compareHighCard(Hand secondPlayerHand) {
//        if(this.highValue == secondPlayerHand.highValue) {
//            if(this.secondValue == secondPlayerHand.secondValue) {
//                if (this.thridValue == secondPlayerHand.thridValue) {
//
//                } else {
//                    return this.thridValue > secondPlayerHand.thridValue;
//                }
//            } else {
//                return this.secondValue > secondPlayerHand.secondValue;
//            }
//
//        } else {
//            return this.highValue > secondPlayerHand.highValue;
//        }
//
//        return false;
//    }
}
