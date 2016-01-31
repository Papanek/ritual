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
    public static void main(String[] args){
        JFrame frame = new JFrame();
        GameWorld panel = new GameWorld(600,400);
        panel.setPreferredSize(new Dimension(600, 400));
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.start();
    }
}
