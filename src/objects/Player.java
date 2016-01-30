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
    private float SPEED = .08f;

    public Player(int x, int y, int health, int maxSpeed) {
        super(x, y, health, maxSpeed);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.translate(x,y);
        g.drawOval(0,0,100,100);
    }

    public void setUp(){
        movingUP = !movingUP;
        speedUp = 0;
    }

    public void setDown(){
        movingDown = !movingDown;
        speedDown = 0;
    }

    public void setLeft(){
        movingLeft = !movingLeft;
        speedLeft = 0;
    }

    public void setRight(){
        movingRight = !movingRight;
        speedRight = 0;
    }

    @Override
    public void move() {
        if(movingUP)this.y -= this.speedUp;
        if(movingDown)this.y += this.speedDown;
        if(movingLeft)this.x -= this.speedLeft;
        if(movingRight)this.x += this.speedRight;
    }

    @Override
    public void moveUp() {
        movingUP = true;
        setDown(); setLeft(); setRight();
        if(speedUp < maxSpeed) {
            speedUp += SPEED;
        }
    }

    @Override
    public void moveDown() {
        movingDown = true;
        setLeft(); setRight(); setUp();
        if(speedDown < maxSpeed) {
            speedDown += SPEED;
        }
    }

    @Override
    public void moveLeft() {
        movingLeft = true;
        setRight(); setUp(); setDown();
        if(speedLeft < maxSpeed) {
            speedLeft += SPEED;
        }
    }

    @Override
    public void moveRight() {
        movingRight = true;
        setLeft(); setUp(); setDown();
        if(speedRight < maxSpeed) {
            speedRight += SPEED;
        }
    }
}