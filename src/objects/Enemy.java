package objects;

import interfaces.Movable;

import java.awt.*;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Enemy extends Creature implements Movable {

    public Enemy(int x, int y, int health, int maxSpeed) {
        super(x, y, health, maxSpeed);
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void move() {

    }
}
