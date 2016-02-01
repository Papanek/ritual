package beings.badguys;

import beings.Humanoid;
import beings.goodguys.Player;
import beings.goodguys.Summoner;
import interfaces.AI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public abstract class Enemy extends Humanoid implements AI {
    public static final int SPIDER = 1;
    private int reboundTimer = 0;
    private float speed = .011f;
    File img;
    BufferedImage characterImage;
    Random rand;

    public Enemy(int x, int y, float maxSpeed, String imgLoc) {
        super(x, y, maxSpeed);
        rand = new Random();
        width = 40;
        height = 40;
        img = new File(imgLoc);
        try {
            characterImage = ImageIO.read(this.getClass().getResourceAsStream("/images/enemybigger.png"));
        } catch (IOException e) {
            System.out.print("enemyfuck");
        }
    }

    @Override
    public void update() {
        move();
        reboundTimer--;
    }

    @Override
    public void draw(Graphics2D g) {
        g.translate(x, y);
        super.drawHealthBar(health, g);
        g.drawImage(characterImage, 0, 0, null);
        g.translate(-x, -y);
    }

    @Override
    public void moveAway() {
        if (reboundTimer < 0) {
            speedY *= -rand.nextFloat() * 1.5;
            speedX *= (rand.nextFloat() * 2)+1;
            reboundTimer = 100;
        }
    }

    private double calcDist(double x1, double x2, double y2, double y1){
        double xsqr = Math.pow((x2-x1),2.0);
        double ysqr = Math.pow((y2-y1),2.0);
        double calc = xsqr + ysqr;
        calc = Math.sqrt(calc);
        return calc;
    }

    @Override
    public void moveTo(Player p, Summoner s) {

        if(calcDist(p.getX(),x,p.getY(),y) < calcDist(s.getX(),x,s.getY(),y)){
            if (p.getX() > x) {
                speedX += speed;
                if (speedX > maxSpeed) {
                    speedX = maxSpeed;
                }
            } else {
                speedX -= speed;
                if (speedX < -maxSpeed) {
                    speedX = -maxSpeed;
                }
            }
            if (p.getY() > y) {
                speedY += speed;
                if (speedY > maxSpeed) {
                    speedY = maxSpeed;
                }
            } else {
                speedY -= speed;
                if (speedY < -maxSpeed) {
                    speedY = -maxSpeed;
                }
            }
        }
        else if(calcDist(p.getX(),x,p.getY(),y) > calcDist(s.getX(),x,s.getY(),y)){
            if (s.getX() > x) {
                speedX += speed;
                if (speedX > maxSpeed) {
                    speedX = maxSpeed;
                }
            } else {
                speedX -= speed;
                if (speedX < -maxSpeed) {
                    speedX = -maxSpeed;
                }
            }
            if (s.getY() > y) {
                speedY += speed;
                if (speedY > maxSpeed) {
                    speedY = maxSpeed;
                }
            } else {
                speedY -= speed;
                if (speedY < -maxSpeed) {
                    speedY = -maxSpeed;
                }
            }
        }


    }
}
