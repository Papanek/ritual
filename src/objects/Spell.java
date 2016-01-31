package objects;

import java.awt.Color;
import java.awt.Graphics2D;

import interfaces.Drawable;
import interfaces.Movable;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/

//TODO kill spell once it is off of screen
public class Spell implements Drawable, Movable{
    protected int damage;
    protected float speedX;
    protected float speedY;
    protected int x, y;
    protected Color spellColor;
    
    public Spell(int x, int y, float speedX, float speedY){
    	this.x = x ; // adjust this to get spell to shoot from staff
    	this.y = y + 50; // and this
    	this.speedX = speedX;
    	this.speedY = speedY;
    	this.spellColor = Color.RED;
    }
    
	@Override
	public void draw(Graphics2D g) {
		g.setColor(spellColor);
		g.translate(x,  y);
		g.drawOval(0, 0, 5, 5);
		g.translate(-x, -y);
	}

	@Override
	public void move() {
		this.x += speedX;
		this.y += speedY;
	}
}
