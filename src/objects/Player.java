package objects;

import interfaces.Controllable;
import interfaces.Movable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Player extends Creature implements Movable, Controllable{
    List<Spell> spells = new LinkedList<>();
    boolean movingX = false;
    boolean movingY = false;

    public Player(){}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.drawOval((int)this.x,(int)this.y,10,10);
    }

    public void setMovingX(){
        movingX = !movingX;
    }

    public void setMovingY(){
        movingY = !movingY;
    }

    @Override
    public void move() {
        if(movingX) {
            this.x += speedX;
        } else {
            speedX = 0;
        }
        if(movingY){
            this.y += speedY;
        }else {
            speedY = 0;
        }
    }

    @Override
    public void moveX(String x) {
        if(x.equals("inc")){
            setMovingX();
            if(Math.abs(speedX) < maxSpeed){
                speedX += 0.3;
            }
        }
        else if(x.equals("dec")){
            if(Math.abs(speedX) < maxSpeed){
                speedX -= 0.3;
            }
        }
    }

    @Override
    public void moveY(String x) {
        if(x.equals("inc")){
            if(Math.abs(speedY) < maxSpeed){
                speedY += 0.3;
            }
        }
        else if(x.equals("dec")){
            if(Math.abs(speedY) < maxSpeed) {
                speedY -= 0.3f;
            }
        }
    }
}