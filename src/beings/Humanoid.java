package beings;

import interfaces.*;

import java.awt.*;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public abstract class Humanoid implements Drawable, Collidable, Destructable, Updatable, Movable {
    protected int health, maxHealth;
    protected int width, height;
    protected double x, y;
    protected float speedX, speedY;
    protected float maxSpeed;
    protected boolean alive;

    public Humanoid(int x, int y, int maxSpeed) {
        this.health = 100;
        this.maxHealth = 100;
        alive = true;
        this.x = x;
        this.y = y;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void move() {
        this.x += speedX;
        this.y += speedY;
    }

    @Override
    public void takeDamage(int damage) {
        this.health -= damage;
        if (health <= 0) {
            alive = false;
        }
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public void drawHealthBar(int health, Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0 - 5, 0 - 5, 50, 10);
        g.setColor(Color.red);
        g.fillRect(0 - 3, 0 - 3, ((50 * health) / maxHealth) - 4, 6);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}