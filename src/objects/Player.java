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
    boolean moving = false;

    public Player(){}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.drawOval((int)this.x,(int)this.y,10,10);
    }

    @Override
    public void move() {
        this.x += speedX;
        this.y += speedY;
    }

    @Override
    public void moveX() {

    }

    @Override
    public void moveY() {

    }
}