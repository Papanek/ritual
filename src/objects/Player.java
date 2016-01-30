package objects;

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
public class Player extends Creature implements Movable{
    List<Spell> spells = new LinkedList<>();

    public Player(){}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.drawOval(10,10,10,10);
    }

    @Override
    public void move() {

    }

    @Override
    public void moveRight() {
        this.x++;
    }

    @Override
    public void moveLeft() {
        this.x--;
    }

    @Override
    public void moveDown() {
        this.y--;
    }

    @Override
    public void moveUp() {
        this.y++;
    }
}