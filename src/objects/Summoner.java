package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Created by brewi on 2016-01-29.
 */
public class Summoner extends Creature{
	
	
    ArrayList<Spell> spells = new ArrayList<>();
	
    public Summoner(){
    	super();
    	this.speedX = 0;
    	this.speedY = 0;
    	this.maxSpeed = 0;
    }

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.drawOval(40, 50, 10, 10);
	}

	@Override
	public void move() {
		//cannot move 
	}
	
	
}
