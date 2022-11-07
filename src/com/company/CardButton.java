package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardButton extends JButton implements ActionListener {
    private Player player;
    private int cardIndex;

    public CardButton(String text, int cardIndex, Player player) {
        this.setText(text);
        this.cardIndex = cardIndex;
        this.player = player;
        this.addActionListener(this);

    }

    public Player getPlayer() {
        return player;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.exchangeCard(cardIndex);
        System.out.println(player.getName());
        player.getHand().printHand();
        System.out.println(cardIndex);
        System.out.println("listener");

    }
}
