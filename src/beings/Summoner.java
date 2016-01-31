package beings;

import magic.Spell;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by brewi on 2016-01-29.
 */
public class Summoner extends Humanoid {

	File img = new File("resource/summonerbigger.png");
	BufferedImage characterImage;
    ArrayList<Spell> spells = new ArrayList<>();

	public Summoner(int x, int y, int health, int maxSpeed) {
		super(x, y, health, maxSpeed);
		try{
			characterImage= ImageIO.read(img);
		}
		catch(IOException e){System.out.print("fuck");}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(characterImage,(int)this.y,(int)this.x,null);
	}
}
