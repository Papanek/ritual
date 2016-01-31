package objects;

import interfaces.AI;
import interfaces.Movable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Enemy extends Creature implements Movable, AI {
    private float speed = .003f;
    File img = new File("resource/enemybigger.png");
    BufferedImage characterImage;

    public Enemy(int x, int y, int health, int maxSpeed) {
        super(x, y, health, maxSpeed);
        try{
            characterImage= ImageIO.read(img);
        }
        catch(IOException e){System.out.print("fuck");}
    }

    @Override
    public void draw(Graphics2D g) {
        //g.setColor(Color.RED);
        g.translate(x,y);
        g.drawImage(characterImage,0,0,null);
        g.translate(-x,-y);
    }

    @Override
    public void move() {
        this.x += speedX;
        this.y += speedY;
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
