package magic;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import interfaces.Collidable;
import interfaces.Destructable;
import interfaces.Drawable;
import interfaces.Movable;
import sun.security.krb5.internal.crypto.Des;

import javax.imageio.ImageIO;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/

public class Spell implements Drawable, Movable, Collidable{
	public static final String FIREBALL = "resource/fireballbigger.png";
    protected int damage = 50;
	protected int health = 1;
    protected float speedX;
    protected float speedY;
    protected int x, y, width = 1, height = 1;
	File img;
	BufferedImage spellImage;

    public Spell(int x, int y, double mx, double my, String imgLoc){
		this.x = x;
		this.y = y;
		speedX = (float) (mx - this.x);
		speedY = (float) (my -this.y);
		double theta = Math.atan2(speedY, speedX);
		speedX = (float) Math.cos(theta)*6;
		speedY = (float) Math.sin(theta)*6;
		img =new File(imgLoc);
		try{
			spellImage= ImageIO.read(img);
		}
		catch(IOException e){System.out.print("fuck");}
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
