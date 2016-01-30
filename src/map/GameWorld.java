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
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class GameWorld extends JPanel implements MouseListener, KeyListener{
    private LinkedList<Drawable> creatures;

    BufferedImage image;

    private Player p;
    public GameWorld(int width, int height){
        image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        p = new Player();
        creatures = new LinkedList<>();
        creatures.add(p);
        addKeyListener(this);
    };

    public void start () {
        while(true){

            p.move();
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e){
                System.out.println("Sleep Interrupted");
            }
        }

    }

    @Override
    public void paint(Graphics _g){
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        drawBackground(g);
        drawCreatures(g);
        _g.drawImage(image,0,0,null);
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
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            System.out.println("Up");
            p.moveUp();
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Left");
            p.moveLeft();
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("Down");
            p.moveDown();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("Right");
            p.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            System.out.println("not moving up");
            p.setUp();
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("not moving left");
            p.setLeft();
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("not moving Down");
            p.setDown();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("not moving Right");
            p.setRight();
        }
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

    }
}
