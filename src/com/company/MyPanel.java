package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.IOException;
import com.company.Card.Suits;


import javax.imageio.ImageIO;
import javax.swing.*;

public class MyPanel extends JPanel {

    int width = 1000;
    int length = 1000;

    String[][] cardsImagesUrls;
    Player[] players;
    GameLogic gameLogic;


    public MyPanel() {
        setPreferredSize(new Dimension(width, length));
        this.gameLogic = new GameLogic();
        gameLogic.start();
        players = gameLogic.getPlayers();


        loadsCardsImages();


    }



    public void loadsCardsImages() {
        cardsImagesUrls = new String[players.length][5];
        for(int i = 0; i < players.length; i++) {
            Hand hand = players[i].getHand();
            Card[] cards = hand.getHand();

            for (int j = 0; j < cards.length; j++) {
                Card card = cards[j];
                String suitSuffix = "";
                Suits suit = card.getSuit();

                if (suit == Suits.SPADES) {
                    suitSuffix += "spades-";
                } else if (suit == Suits.CLUBS) {
                    suitSuffix += "clubs-";
                } else if (suit == Suits.DIAMONDS) {
                    suitSuffix += "diamonds-";
                } else {
                    suitSuffix += "hearts-";
                }

                int value = cards[j].getValue().getCardValue();
                String valueSuffix = "";

                if (value == 14) {
                    valueSuffix += "a";
                } else if (value == 13) {
                    valueSuffix += "k";
                } else if (value == 12) {
                    valueSuffix += "q";
                } else if (value == 11) {
                    valueSuffix += "j";
                } else {
                    valueSuffix += Integer.toString(value);
                }

                String url = "/svgdeck/75/";
                url += suitSuffix;
                url += valueSuffix;
                url += "-75.png";

                cardsImagesUrls[i][j] = url;

            }

        }

    }




    public void paintComponent(Graphics g) {
         BufferedImage image = null;
        Graphics2D g2D = (Graphics2D) g;
        super.paintComponent(g);
         int x = 0;
         int y = 50;
        try {
            for(int i =0; i < players.length; i++  ) {
                int button = 1;
                g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
                g.drawString(players[i].getName() + players[i].getAmountOfTokens(),100,y - 20);
                TokenSpinner spinner = new TokenSpinner(players[i].getAmountOfTokens(),x, y - 50 );
                RaiseButton raiseButton = new RaiseButton(x, y-30, spinner, players[i]);
                raiseButton.addActionListener(raiseButton);
                add(spinner);
                add(raiseButton);
                setLayout(null);


                String currentBetText = gameLogic.getCurrentBetText();
                g.drawString(currentBetText, 500, y - 20);
                for (int j = cardsImagesUrls[i].length-1; j >= 0; j--) {

                    String text = String.valueOf(button);
                    //System.out.println(cardsImagesUrls[i][j]);
                    image = ImageIO.read(getClass().getResource(cardsImagesUrls[i][j]));
                    g.drawImage(image, x, y, null);


                    CardButton b = new CardButton(text,j,players[i]);
                    b.setBounds(x + 15,y + 120,30,30);
                    loadsCardsImages();
                    repaint();
                    add(b);
                    setLayout(null);
                    x += 75;
                    button++;
                }
                x = 0;
                y += 300;
            }

            CheckHandsButton checkHandsButton = new CheckHandsButton();
            add(checkHandsButton);
            checkHandsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameLogic.checkWinner();
                    String result = gameLogic.getResult();
                    System.out.println(result);
                    showMessageDialog(null, result);
                    gameLogic.nextRound();
                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}