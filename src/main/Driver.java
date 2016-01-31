package main;

import map.GameWorld;

import javax.swing.*;
import java.awt.*;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Driver {
    public static JFrame frame = new JFrame();

    public static void main(String[] args){
        startGame();
    }

    public static void startGame(){
        GameWorld panel = new GameWorld(800,600);
        panel.setPreferredSize(new Dimension(800, 600));
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.setUpGame();
    }

    public static void closeApp(){
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }
}
