package beings;

import interfaces.AI;
import interfaces.Movable;

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
public class Enemy extends Humanoid implements Movable, AI {
    public static final String SPIDER = "resource/enemybigger.png";
    private int reboundTimer = 0;
    private float speed = .006f;
    File img;
    BufferedImage characterImage;
    Random rand;

    public Enemy(int x, int y, int maxSpeed, String imgLoc) {
        super(x, y, maxSpeed);
        rand = new Random();
        width = 40; height = 40;
        img = new File(imgLoc);
        try{
            characterImage= ImageIO.read(img);
        }
        catch(IOException e){System.out.print("fuck");}
    }

    @Override
    public void draw(Graphics2D g) {
        g.translate(x,y);
        super.drawHealthBar(health, g);
        g.drawImage(characterImage,0,0,null);
        g.translate(-x,-y);
    }

    @Override
    public void move() {
        this.x += speedX;
        this.y += speedY;
        reboundTimer--;
    }

    @Override
    public void moveAway() {
        if(reboundTimer<0) {
            speedY *= -rand.nextFloat()*2;
            speedX *= rand.nextFloat();
            reboundTimer=100;
        }
    }

    @Override
    public void moveTo(Player p, Summoner s) {
        if(p.getX()>x){
            speedX += speed;
            if(speedX>maxSpeed){
                speedX=maxSpeed;
            }else if(speedX < 0){
            	speedX += speed;
            }
        } else {
            speedX -= speed;
            if(speedX<-maxSpeed){
                speedX = -maxSpeed;
            }else if(speedX > 0){
            	speedX -= speed;
            }
        }
        if(p.getY()>y){
            speedY += speed;
            if(speedY>maxSpeed){
                speedY=maxSpeed;
            }else if(speedY < 0){
            	speedY += speed;
            }
        } else {
            speedY -= speed;
            if(speedY<-maxSpeed){
                speedY = -maxSpeed;
            }else if (speedY > 0){
            	speedY -= speed;
            }
        }
    }

}
