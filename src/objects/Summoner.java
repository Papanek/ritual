package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by brewi on 2016-01-29.
 */
public class Summoner extends Creature{
	
    List<Spell> spells = new LinkedList<>();
	//TODO spells
	
	@Override
	public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.drawOval(10,10,10,10);
	}
	
	public Summoner(){
		this.speedX = 0;
		this.speedY = 0;
	}
}
