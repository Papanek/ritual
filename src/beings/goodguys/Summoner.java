package beings.goodguys;

import beings.Humanoid;
import magic.Spell;
import magic.waves.SummonerWave;

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
    private int flinchCooldown;
    private int currentSpellCooldown;
    private int currentPrimarySpell;

    public Summoner(int x, int y, int maxSpeed) {
        super(x, y, maxSpeed);
        currentPrimarySpell = Spell.SUMMONERWAVE;
        try {
            characterImage = ImageIO.read(img);
        } catch (IOException e) {
            System.out.print("summonerfuck");
        }
    }

    @Override
    public void update() {
        currentSpellCooldown--;
    }

    @Override
    public void draw(Graphics2D g) {
        g.translate(x, y);
        g.drawImage(characterImage, 0, 0, null);
        super.drawHealthBar(health, g);
        flinchCooldown--;
        g.translate(-x,-y);
    }

    @Override
    public void takeDamage(int damage) {
        System.out.println("player health: " + health);
        if (flinchCooldown <= 0) {
            health -= damage;
            flinchCooldown = 20;
        }
        if (health <= 0) {
            alive = false;
        }
    }

    public Spell castSpell() {
        if (currentSpellCooldown <= 0) {
            if (currentPrimarySpell == Spell.SUMMONERWAVE) {
                currentSpellCooldown = 100;
                return new SummonerWave();
            }
        }
        return null;
    }
}
