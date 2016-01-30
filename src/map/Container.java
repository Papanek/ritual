package map;

import interfaces.Drawable;
import objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Container extends Canvas implements Runnable, MouseListener, KeyListener{
    private LinkedList<Drawable> creatures;
    private JFrame frame;
    private Player p;
    public Container(int width, int height){
        frame = new JFrame("Game");
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(this);
        frame.setVisible(true);
        p = new Player();
        creatures = new LinkedList<>();
        creatures.add(p);
        addKeyListener(this);
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
            p.move();
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
        drawBackground(g);
        drawCreatures(g);

    }

    private void drawCreatures(Graphics2D g){
        for(Drawable d : creatures){
            d.draw(g);
        }
    }

    private void drawBackground(Graphics2D g){
        g.setColor(Color.CYAN);
        g.fillRect(0,0,800,600);
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

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            System.out.println("Up");
            p.setMovingY();
            p.moveY("dec");
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Left");
            p.setMovingX();
            p.moveX("dec");
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("Down");
            p.setMovingY();
            p.moveY("inc");
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("Right");
            p.moveX("inc");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            p.setMovingY();
            System.out.println("not moving up");
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("not moving left");
            p.setMovingX();
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("not moving Down");
            p.setMovingY();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("not moving Right");
            p.setMovingX();
        }
    }
}
