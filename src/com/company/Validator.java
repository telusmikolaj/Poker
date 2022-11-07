package com.company;

import java.util.*;

public class Validator {


    private Card.Suits[] suits;
    private Card.Values[] values;
    private Card currentCard;
    private Hand currentHand;
    private Player winner;
    private static final int handSize = 5;

    public Validator() {

    }




    public void checkHands(Player[] players) {

        players[1].getHand().printHand();
        for (int i = 0; i < players.length; i++) {
            currentHand = players[i].getHand();
            currentHand.setHandRankings(Hand.HandRankings.NONE);
            currentHand.setHighValue(0);
            Card[] currHandCards = currentHand.getHand();
            Arrays.sort(currHandCards);
            suits = new Card.Suits[handSize];
            values = new Card.Values[handSize];
            for (int j = 0; j < handSize; j++) {
                currentCard = currHandCards[j];
                suits[j] = currentCard.getSuit();
                values[j] = currentCard.getValue();

            }
            checkStraightFlush();
            checkFourOfAKind();
            checkFullHouse();
            checkFlush();
            checkStraight();
            checkTrips();
            checkTwoPair();
            checkOnePair();
            checkHighCard();
        }

    }

    public Player getWinner(Player[] players) {

        checkHands(players);
        int firstPlayerRanking = players[0].getHand().getHandRankings();
        int secondPlayerRanking = players[1].getHand().getHandRankings();
        Player firstPlayer = players[0];
        Player secondPlayer = players[1];




        if(firstPlayerRanking > secondPlayerRanking) {
            winner = players[0];
        } else if(secondPlayerRanking > firstPlayerRanking) {
            winner = players[1];
        } else {
            Hand.HandRankings playersHandRankings = players[0].getHand().handRankings;
            Hand firstPlayerHand = firstPlayer.getHand();
            Hand secondPlayerHand = secondPlayer.getHand();
            if(playersHandRankings == Hand.HandRankings.HIGHCARD) {
                if (firstPlayerHand.getHighValue() > secondPlayerHand.getHighValue()) {
                    winner = firstPlayer;
                } else {
                    winner = secondPlayer;
                }
            }

            if(playersHandRankings == Hand.HandRankings.ONEPAIR) {
                if (firstPlayerHand.getHighValue() > secondPlayerHand.getHighValue()) {
                    winner = firstPlayer;
                } else {
                    winner = secondPlayer;
                }
            }

            if(playersHandRankings == Hand.HandRankings.TWOPAIRS) {
                if (firstPlayerHand.getHighValue() > secondPlayerHand.getHighValue()) {
                    winner = firstPlayer;
                } else {
                    winner = secondPlayer;
                }
            }

            if(playersHandRankings == Hand.HandRankings.TRIPS) {
                if (firstPlayerHand.getHighValue() > secondPlayerHand.getHighValue()) {
                    winner = firstPlayer;
                } else {
                    winner = secondPlayer;
                }
            }

            if(playersHandRankings == Hand.HandRankings.STRAIGHT) {
                if (firstPlayerHand.getHighValue() > secondPlayerHand.getHighValue()) {
                    winner = firstPlayer;
                } else {
                    winner = secondPlayer;
                }
            }

            if(playersHandRankings == Hand.HandRankings.FLUSH) {
                if (firstPlayerHand.getHighValue() > secondPlayerHand.getHighValue()) {
                    winner = firstPlayer;
                } else {
                    winner = secondPlayer;
                }
            }

            if(playersHandRankings == Hand.HandRankings.QUADS) {
                if (firstPlayerHand.getHighValue() > secondPlayerHand.getHighValue()) {
                    winner = firstPlayer;
                } else {
                    winner = secondPlayer;
                }
            }

        }

        return winner;

    }


    public boolean checkStraightFlush() {
        if (checkStraight() && checkFlush()) {
            if(currentHand.getHandRankings() < Hand.HandRankings.STRAIGHTFLUSH.getRank()) {
                currentHand.setHandRankings(Hand.HandRankings.STRAIGHTFLUSH);
                return true;
            }
        }

        return false;
    }

    public boolean checkStraight() {
        for (int i = 0; i < handSize-1; i++) {
            int currentCardRank = values[i].getCardValue();
            int nextCardRank = values[i+1].getCardValue();

            if(currentCardRank + 1 != nextCardRank) {
                return false;
            }
        }
        int firstCardInHandValue = values[4].getCardValue();
        currentHand.setHighValue(firstCardInHandValue);
        if(currentHand.getHandRankings() < Hand.HandRankings.STRAIGHT.getRank()) {
            currentHand.setHandRankings(Hand.HandRankings.STRAIGHT);
        }
        return  true;
    }

    public boolean checkFourOfAKind() {
        for (int i = 0; i < handSize-1; i++) {
            int currentCardRank = values[i].getCardValue();
            int equalsRanks = 0;
            for (int j = i + 1; j < handSize; j++) {
                int nextRank = values[j].getCardValue();
                if (currentCardRank == nextRank) {
                    equalsRanks++;
                }

                if(equalsRanks == 3) currentHand.setHighValue(currentCardRank);
            }
            if (equalsRanks == 3) {
                if(currentHand.getHandRankings() < Hand.HandRankings.QUADS.getRank()) {
                    currentHand.setHandRankings(Hand.HandRankings.QUADS);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkFullHouse() {
        int tripsCard = 0;
        if(checkTrips()) {
            tripsCard = currentHand.getHighValue();
            if(checkPairForFull(tripsCard)) {
                if(currentHand.getHandRankings() < Hand.HandRankings.FULLHOUSE.getRank()) {
                    currentHand.setHandRankings(Hand.HandRankings.FULLHOUSE);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkFlush() {
        for (int i = 0; i < handSize-1; i++) {
            Card.Suits currentCardSuit = suits[i];
            Card.Suits nextCardSuit = suits[i +1];
            if (currentCardSuit != nextCardSuit) {
               return false;
            }
        }
        if(currentHand.getHandRankings() < Hand.HandRankings.FLUSH.getRank()) {
            currentHand.setHandRankings(Hand.HandRankings.FLUSH);
        }
        return true;
    }

    public boolean checkTrips() {
        for (int i = 0; i < handSize; i++) {
            int currentCardRank = values[i].getCardValue();
            int equalsRanks = 0;
            for (int j = i + 1; j < handSize; j++) {
                int nextRank = values[j].getCardValue();
                if (currentCardRank == nextRank) equalsRanks++;
                if (equalsRanks == 2 ) currentHand.setHighValue(currentCardRank);
            }
            if (equalsRanks == 2) {
                if(currentHand.getHandRankings() < Hand.HandRankings.TRIPS.getRank()) {
                    currentHand.setHandRankings(Hand.HandRankings.TRIPS);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkOnePair() {
        for (int i = 0; i < handSize-1; i++) {
            int currentCardRank = values[i].getCardValue();
            int equalsRanks = 0;
            for (int j = i + 1; j < handSize; j++) {
                int nextRank = values[j].getCardValue();
                if (currentCardRank == nextRank) equalsRanks++;
                if (equalsRanks == 1 && currentHand.getHighValue() == 0) currentHand.setHighValue(currentCardRank);
                if (equalsRanks == 1) {
                    if(currentHand.getHandRankings() < Hand.HandRankings.ONEPAIR.getRank()) {
                        currentHand.setHandRankings(Hand.HandRankings.ONEPAIR);
                    }
                }
            }

        }
        return false;
    }

    public boolean checkPairForFull(int rank) {
        boolean result = false;
        for (int i = 0; i < handSize-1; i++) {
            int currentCardRank = values[i].getCardValue();
            int equalsRanks = 0;
            for (int j = i + 1; j < handSize; j++) {
                int nextRank = values[j].getCardValue();
                if (currentCardRank == nextRank && currentCardRank != rank) equalsRanks++;
            }
            if (equalsRanks == 1) {
                result = true;
            }
        }
        return result;
    }

    public boolean checkTwoPair() {
        int check[] = new int[2];
        int counter = 0;
        for (int i = 0; i < handSize; i++) {
            int currentCardRank = values[i].getCardValue();
            int equalsRanks = 0;
            for (int j = i + 1; j < values.length; j++) {
                int nextCardRank = values[j].getCardValue();
                if (currentCardRank == nextCardRank) equalsRanks++;
            }
            if (equalsRanks == 1) {
                check[counter] = 1;
                counter++;
            }
        }
        if (check[0] == 1 && check[1] == 1) {
            if(currentHand.getHandRankings() < Hand.HandRankings.TWOPAIRS.getRank()) {
                currentHand.setHandRankings(Hand.HandRankings.TWOPAIRS);
                return true;
            }
        }
        return false;
    }

    public boolean checkHighCard() {
        if(currentHand.handRankings.getRank() == -1) {
            currentHand.setHandRankings(Hand.HandRankings.HIGHCARD);
            currentHand.setCardsValues();
            return true;
        }

        return false;
    }




}



