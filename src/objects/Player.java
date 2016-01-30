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
    boolean movingUP = false, movingDown = false, movingLeft = false, movingRight = false;
    private float SPEED = .06f;

    public Player(){}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.translate(x,y);
        g.drawOval(0,0,100,100);
    }

    public void setUp(){
        movingUP = !movingUP;
    }

    public void setDown(){
        movingDown = !movingDown;
    }

    public void setLeft(){
        movingLeft = !movingLeft;
    }

    public void setRight(){
        movingRight = !movingRight;
    }

    @Override
    public void move() {
        this.x += this.speedX;
        this.y += this.speedY;
    }

    @Override
    public void moveUp() {
        movingUP = true;
        speedY -= SPEED;
    }

    @Override
    public void moveDown() {
        movingUP = true;
        speedY += SPEED;
    }

    @Override
    public void moveLeft() {
        movingUP = true;
        speedX -= SPEED;
    }

    @Override
    public void moveRight() {
        movingUP = true;
        speedX += SPEED;
    }
}