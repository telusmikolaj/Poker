package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CheckHandsButton extends JButton {

    private GameLogic gameLogic;

    public CheckHandsButton() {
        this.setText("Check Hands");
        this.setBounds(500,500, 100, 30);
    }
}
