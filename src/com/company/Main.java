package com.company;


import javax.swing.*;
import java.awt.*;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MyFrame();
            }
        });
    }
}
