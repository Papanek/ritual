package map;

import interfaces.Drawable;
import objects.CollisionDetector;
import objects.Enemy;
import objects.Player;
import objects.Summoner;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class GameWorld extends JPanel implements MouseListener, KeyListener{
    CollisionDetector detector;
    private LinkedList<Enemy> enemies;
    File img = new File("resource/background.png");
    BufferedImage characterImage, backgroundImg;
    private Player p;
    public GameWorld(int width, int height){
        detector = new CollisionDetector();
        try{
            backgroundImg = ImageIO.read(img);
        }
        catch(IOException e){System.out.print("");}
        characterImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        p = new Player(10,10,10,2);
        enemies = new LinkedList<>();
        for(int i = 0; i<2; i++){
            enemies.add(new Enemy(10*i,10*i,100,1));
        }
        addKeyListener(this);
        addMouseListener(this);
    };

    public void start () {
        while(true){
            moveEnemies();
            p.move();
            detector.detectPlayerCollision(p,enemies);
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
        Graphics2D g = characterImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        drawBackground(g);
        drawCreatures(g);
        _g.drawImage(characterImage,0,0,null);
        g.dispose();
    }

    private void drawCreatures(Graphics2D g){
        p.draw(g);
        for(Drawable d : enemies){
            d.draw(g);
        }
    }

    private void drawBackground(Graphics2D g){
        g.drawImage(backgroundImg,0,0,null);
    }

    private void moveEnemies(){
        for(Enemy e : enemies){
            e.moveTo(p, new Summoner(10,10,10,10));
            e.move();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            System.out.println("Up");
            p.moveUp(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("Left");
            p.moveLeft(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("Down");
            p.moveDown(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("Right");
            p.moveRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            System.out.println("not moving up");
            p.moveUp(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            System.out.println("not moving left");
            p.moveLeft(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            System.out.println("not moving Down");
            p.moveDown(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            System.out.println("not moving Right");
            p.moveRight(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	System.out.println(mouseX + " " + mouseY);
    	  if(e.getButton() == MouseEvent.BUTTON1) {
    		  p.castSpell(mouseX, mouseY);
    	  }else if(e.getButton() == MouseEvent.BUTTON3){
    		  p.teleport(mouseX, mouseY);
    	  }
  	    
    	
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
