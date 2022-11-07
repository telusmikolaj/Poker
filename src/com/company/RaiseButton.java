package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RaiseButton extends JButton implements ActionListener {

    private TokenSpinner spinner;
    private Player player;
    public RaiseButton(int x, int y, TokenSpinner spinner, Player player) {
        this.spinner = spinner;
        this.player = player;
        this.setText("bet");
        this.setBounds(x,y,100,20);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int bet = (int) spinner.getValue();
        int playerTokens = player.getAmountOfTokens() - bet;
        player.setAmountOfTokens(playerTokens);
        player.setCurrentBet(bet);

    }
}
