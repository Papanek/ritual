package beings;

import interfaces.Collidable;
import interfaces.Destructable;
import interfaces.Drawable;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public abstract class Humanoid implements Drawable, Collidable, Destructable {
    protected int health, width, height;
    protected double x, y;
    protected float speedX, speedY;
    protected float speedUp, speedDown, speedLeft, speedRight;
    protected float maxSpeed;
    protected boolean alive;
    public Humanoid(int x, int y, int health, int maxSpeed){
        this.health = health;
        alive = true;
        this.x = x;
        this.y = y;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void takeDamage(int damage){
        this.health -= damage;
        if(health<0){
            alive = false;
        }
    }
}