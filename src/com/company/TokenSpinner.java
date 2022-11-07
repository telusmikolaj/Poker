package com.company;

import javax.swing.*;

public class TokenSpinner extends JSpinner {

    public TokenSpinner(int max, int x, int y) {
        SpinnerNumberModel model = new SpinnerNumberModel(1,0,max,100);
        this.setModel(model);
        this.setBounds(x,y,100,20);
    }
}
