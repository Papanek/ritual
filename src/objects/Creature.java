package objects;

import interfaces.Drawable;
import interfaces.Movable;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public abstract class Creature implements Drawable {
    protected int health;
    protected float x, y;
    protected float speedX, speedY;

}
