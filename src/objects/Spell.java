package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import interfaces.Drawable;
import interfaces.Movable;

import javax.imageio.ImageIO;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/

public class Spell implements Drawable, Movable{
    protected int damage;
    protected float speedX;
    protected float speedY;
    protected int x, y;
    protected Color spellColor;
	File img = new File("resource/fireballbigger.png");
	BufferedImage spellImage;

    public Spell(int x, int y, float speedX, float speedY){
    	this.x = x; // adjust this to get spell to shoot from staff
    	this.y = y; // and this
    	this.speedX = speedX;
    	this.speedY = speedY;
    	this.spellColor = Color.RED;
		try{
			spellImage= ImageIO.read(img);
		}
		catch(IOException e){System.out.print("fuck");}
    }
    
	@Override
	public void draw(Graphics2D g) {
		//g.setColor(spellColor);
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
		if(this.x > 800 || this.y > 600){
			return false;
		}
		return true;
	}
}
