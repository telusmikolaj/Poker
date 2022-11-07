package com.company;

import javax.swing.*;
import java.util.Scanner;

public class GameLogic {

    private int roundsNum;
    private int playersNum;
    private Player[] players;
    private Validator validator;
    Scanner scanner = new Scanner(System.in);
    private  Deck deck = new Deck();
    private int round;
    private int currentBet;
    private Player winner;



    public GameLogic() {

        validator = new Validator();
        round = 0;
    }

    public void start(){
        getPlayerNumbersFromUser();
    }

    public void getPlayerNumbersFromUser() {
        System.out.println("Enter number of players: ");
        playersNum = scanner.nextInt();
        createPlayers(playersNum);

    }

    public String getCurrentBetText() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current bet: \n");
        sb.append(players[0].getName() + ": " + players[0].getCurrentBet() + "\n");
        sb.append(players[1].getName() + ": " + players[1].getCurrentBet() + "\n");

        return sb.toString();
    }

    public void createPlayers(int playersNum) {
        players = new Player[playersNum];

        for(int i =0; i < playersNum; i++) {
            System.out.println(i + 1 + "player name: ");
            String playerName = scanner.next();
            players[i] = new Player(playerName, deck);
            players[i].getHand().printHand();
        }
    }

    public void nextRound() {

        System.out.println("Next round - y/n ?" );
        char userInput = scanner.next().charAt(0);
        if(userInput == 'y') {
            for (int i = 0; i < playersNum; i++) {
                players[i].reshuffle();
            }
        }


    }


    public String getResult() {
        String result = "";
        if(winner == null) {
            return result;
        } else {
            Hand winnerHand = winner.getHand();
            StringBuilder sb = new StringBuilder();
            sb.append("The winner is: ");
            sb.append(winner.getName());
            sb.append(" with hand: ");
            sb.append(winnerHand.handRankings);
            sb.append(" of ");
            sb.append(winnerHand.getHighValue());
            sb.append("\n wins " + currentBet);
            result = sb.toString();

        }
        return result;

    }

    public void checkWinner() {
        this.winner = validator.getWinner(players);

    }

    public Hand[] getPlayersHands() {
        Hand[] hands = new Hand[playersNum];

        for (int i = 0; i < hands.length; i++  ) {
            hands[i] = players[i].getHand();
        }

        return hands;
    }

//    public void checkWinner(int[] handsRank) {
//        int max = -1;
//        int winnerNum = -1;
//        for(int i =0; i < handsRank.length; i++  ) {
//            if(max < handsRank[i]) {
//                max = handsRank[i];
//                winnerNum = i;
//            }
//        }
//
//        System.out.println("Player " + players[winnerNum].getName() +
//                " wins");
//    }

//    public void nextRound() {
//        for(Player player : players) {
//            player.reshuffle();
//        }
//    }

    public Player[] getPlayers() {
        return players;
    }


}
