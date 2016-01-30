package objects;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by brewi on 2016-01-29.
 */
public class Summoner extends Creature{


    ArrayList<Spell> spells = new ArrayList<>();

	public Summoner(int x, int y, int health, int maxSpeed) {
		super(x, y, health, maxSpeed);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		g.drawOval(40, 50, 10, 10);
	}
}
