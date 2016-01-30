package objects;

import interfaces.Drawable;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public abstract class Creature implements Drawable {
    protected int health;
    protected double x, y;
    protected float speedX, speedY;
    protected float maxSpeed;
    public Creature(int x, int y, int health, int maxSpeed){
        this.health = health;
        this.x = x;
        this.y = y;
        this.maxSpeed = maxSpeed;
    }

    public int getHealth() {
        return health;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }
}