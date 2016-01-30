package map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Container extends Canvas implements Runnable, MouseListener {
    private int k =0;
    private JFrame frame;
    public Container(int width, int height){
        frame = new JFrame("Game");
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        run();
    };

    @Override
    public void run() {
        while(true){
            BufferStrategy buf = getBufferStrategy();
            if(buf==null){
                createBufferStrategy(3);
                continue;
            }
            Graphics2D g = (Graphics2D) buf.getDrawGraphics();
            render(g);
            buf.show();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e){
                System.out.println("Sleep Interrupted");
            }
        }

    }

    private void render(Graphics2D g){
        g.setColor(Color.CYAN);
        g.fillRect(0,0,800,600);
        g.setColor(Color.black);
        g.drawOval(10,10+k++,10,10);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
