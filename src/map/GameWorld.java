package map;

import beings.badguys.Enemy;
import beings.badguys.Spider;
import beings.goodguys.Player;
import beings.goodguys.Summoner;
import effects.Effect;
import interfaces.Drawable;
import magic.Spell;
import main.Driver;
import scoring.HighScore;
import scoring.Score;
import scoring.Timer;
import util.CollisionDetector;
import util.ScoreKeeper;
import util.MobSpawner;
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
import java.util.*;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class GameWorld extends JPanel implements MouseListener, KeyListener {
    CollisionDetector detector;
    private LinkedList<Enemy> enemies;
    private LinkedList<Spell> spells;
    private LinkedList<Effect> effects;
    private boolean running;
    ImageIcon img;
    BufferedImage characterImage, backgroundImg;
    private Player player;
    private Summoner summoner;
    private Score score;
    private Timer timer;
    private HighScore highScore;
    MobSpawner spawner;

    public GameWorld(int width, int height) {
        running = true;
        detector = new CollisionDetector();
        highScore = new HighScore();
        //try {
        try {
            backgroundImg = ImageIO.read(this.getClass().getResourceAsStream("/images/background.png"));
        } catch (IOException e) {
            System.out.println("backgroundfuck");
        }
        //} catch (IOException e) {
            //System.out.print("");
        //}
        characterImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        addKeyListener(this);
        addMouseListener(this);
    }

    public void startGame() {
        while(running){
            if(!player.isAlive() || !summoner.isAlive()){
                gameOver(true);
            }
            player.update();
            addEnemies();
            updateEnemies();
            updateSummoner();
            updateSpells();
            updateEffects();
            updateScoring();
            detector.detectPlayerEnemyCollision(player, enemies);
            detector.detectSpellCollision(spells, enemies);
            detector.detectSummonerCollision(summoner, enemies);
            detector.detectPlayerSummonerCollision(player, summoner);
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Sleep Interrupted");
            }
        }
    }

    private synchronized void addEnemies(){
        Enemy[] en = spawner.spawnEndless();
        for (Enemy e : en) {
            if(e!=null) {
                enemies.add(e);
            }
        }
    }

    public void setUpGame(){
        player = new Player(10,10,2);
        summoner = new Summoner(375, 275, 0);
        enemies = new LinkedList<>();
        spells = new LinkedList<>();
        effects = new LinkedList<>();
        spawner = new MobSpawner();
        timer = new Timer();
        score = new Score(timer);
        running = true;
        startGame();
    }

    @Override
    public void paint(Graphics _g) {
        Graphics2D g = characterImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        drawBackground(g);
        drawCreatures(g);
        drawEffects(g);
        drawSummoner(g);
        drawSpells(g);
        drawScoreTime(g);
        drawHighScore(g);
        _g.drawImage(characterImage, 0, 0, null);
        g.dispose();
    }

    private void gameOver(boolean over){
        if(over){
            running=false;
            int output = JOptionPane.showConfirmDialog(null,"Game over, would you like to restart the game?","You Lost",JOptionPane.YES_NO_OPTION);
            if(output == JOptionPane.YES_OPTION){
                highScore.addScore(score);
                setUpGame();
            }
            else if(output == JOptionPane.NO_OPTION){
                Driver.closeApp();
            }
        }
    }

    private void drawCreatures(Graphics2D g){
        player.draw(g);
        for(int i=0; i<enemies.size(); i++){
            enemies.get(i).draw(g);
        }
    }

    private void drawSummoner(Graphics2D g) {
        summoner.draw(g);
    }

    private void drawSpells(Graphics2D g) {
        for(int i=0; i<spells.size(); i++){
            spells.get(i).draw(g);
        }
    }

    private void drawEffects(Graphics2D g){
        for(int i=0; i<effects.size(); i++){
            effects.get(i).draw(g);
        }
    }

    private void drawBackground(Graphics2D g) {
        g.drawImage(backgroundImg, 0, 0, null);
    }

    private void drawScoreTime(Graphics2D g){
        String scoreStr = "Time: " + Integer.toString(timer.getTimer()) + "     " +
                "Score: " + score.getScore() + "        " +
                "Enemies Killed: " + score.getEnemiesKilled() + "       ";
        g.setColor(Color.black);
        Font trb = new Font("TimesRoman", Font.BOLD, 18);
        g.setFont(trb);
        g.drawString(scoreStr, 10, 20);
    }

    private void drawHighScore(Graphics2D g){
        String highScoreStr = "High Score: " + highScore.getHighScore();
        g.drawString(highScoreStr, 620, 20);
    }

    private void updateSpells() {
        for (int i = 0; i < spells.size(); i++) {
            Spell s = spells.get(i);
            if (s.isInGameWorld()) {
                s.update();
            } else {
                spells.remove(s);
            }
        }
    }

    private void updateEffects(){
        for (int i = 0; i < effects.size(); i++) {
            Effect e = effects.get(i);
            if (e.isAlive()) {
                e.update();
            } else {
                effects.remove(e);
            }
        }
    }

    private void updateScoring(){
        timer.update();
        score.update();
        highScore.update();
    }

    private void updateSummoner(){
        summoner.update();
    }

    private void updateEnemies() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (e.isAlive()) {
                if (detector.detectEnemyEnemyCollision(e, enemies)) {
                    e.moveAway();
                }
                e.moveTo(player, summoner);
                e.update();
            } else {
                enemies.remove(e);
                score.killedEnemy();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.move('U', true);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.move('L', true);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.move('D', true);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.move('R', true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            player.move('U', false);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            player.move('L', false);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            player.move('D', false);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            player.move('R', false);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            Spell spell = summoner.castSpell();
            if (spell != null) {
                spells.add(spell);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        Spell spell = null;
        if (e.getButton() == MouseEvent.BUTTON1) {

            spell = player.castPrimarySpell(mouseX, mouseY);

        } else if (e.getButton() == MouseEvent.BUTTON3) {

            spell = player.castSecondarySpell(mouseX, mouseY);

        }
        if (spell != null) {
            if(spell.getEffect()!=null) {
                effects.add(spell.getEffect());
                System.out.print("Effect");
            }
            spells.add(spell);
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
