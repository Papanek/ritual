package magic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import interfaces.*;

import javax.imageio.ImageIO;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/

public abstract class Spell implements Drawable, Movable, Collidable, Updatable{
	public static final int FIREBALL = 1;
	public static final int TELEPORT = 2;
    protected int damage = 50;
	protected int health = 1;
    protected float speedX;
    protected float speedY;
    protected int x, y, width = 1, height = 1;
	protected File img;
	protected BufferedImage spellImage;
    public Spell(String imgLoc){
		img =new File(imgLoc);
		try{
			spellImage= ImageIO.read(img);
		}
		catch(IOException e){System.out.print("spellfuck");}
    }

	public int getDamage(){
		return damage;
	}

	public void killSpell(){
		this.x = 2000;
		this.y = 2000;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public double getY() {
		return this.y;
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
	public void draw(Graphics2D g) {
		g.translate(x,  y);
		g.drawImage(spellImage, 0, 0, null);
		g.translate(-x, -y);
	}

	@Override
	public void move() {
		this.x += speedX;
		this.y += speedY;
	}

	public boolean isInGameWorld(){
		return !(this.x > 800 || this.y > 600);
	}
}
