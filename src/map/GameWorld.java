package map;

import interfaces.Drawable;
import beings.*;
import magic.Spell;
import util.CollisionDetector;

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
    private LinkedList<Spell> spells;
    File img = new File("resource/background.png");
    BufferedImage characterImage, backgroundImg;
    private Player player;
    private Summoner summoner;
    public GameWorld(int width, int height){
        detector = new CollisionDetector();
        try{
            backgroundImg = ImageIO.read(img);
        }
        catch(IOException e){System.out.print("");}
        characterImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        player = new Player(10,10,2);
        summoner = new Summoner(375, 275, 0);
        enemies = new LinkedList<>();
        spells = new LinkedList<>();

        for(int i = 0; i<20; i++){
            enemies.add(new Enemy(100*i,100*i,2,Enemy.SPIDER));
        }


        addKeyListener(this);
        addMouseListener(this);
    }

    public void start () {
        while(true){
            moveEnemies();
            moveSpells();
            player.move();
            detector.detectPlayerEnemyCollision(player,enemies);
            detector.detectSpellCollision(spells,enemies);
            detector.detectSummonerCollision(summoner,enemies);
            detector.detectPlayerSummonerCollision(player,summoner);
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
        drawSpells(g);
        drawSummoner(g);
        _g.drawImage(characterImage,0,0,null);
        g.dispose();
    }

    private void drawSpells(Graphics2D g){
        for (Spell s : spells) {
            s.draw(g);
        }
    }

    private void moveSpells(){
        for(int i = 0; i < spells.size(); i++){
            Spell s = spells.get(i);
            if(s.isInGameWorld()){
                s.move();
            }else{
                spells.remove(s);
            }
        }
    }

    private void drawCreatures(Graphics2D g){
        player.draw(g);
        for(Drawable d : enemies){
            d.draw(g);
        }
    }

    private void drawBackground(Graphics2D g){
        g.drawImage(backgroundImg,0,0,null);
    }

    private void drawSummoner(Graphics2D g){
        summoner.draw(g);
    }

    private void moveEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if(e.isAlive()) {
                if (detector.detectEnemyEnemyCollision(e, enemies)) {
                    e.moveAway();
                }
                e.moveTo(player, summoner);
                e.move();
            } else {
                enemies.remove(e);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            player.moveUp(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            player.moveLeft(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            player.moveDown(true);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            player.moveRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_W){
            player.moveUp(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            player.moveLeft(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            player.moveDown(false);
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            player.moveRight(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    	int mouseX = e.getX();
    	int mouseY = e.getY();
    	  if(e.getButton() == MouseEvent.BUTTON1) {

              spells.add(player.castSpell(mouseX,mouseY));

    	  }else if(e.getButton() == MouseEvent.BUTTON3){
    		  player.teleport(mouseX, mouseY);
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
