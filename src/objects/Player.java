package objects;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Player extends Creature {
    List<Spell> spells = new LinkedList<>();

    public Player(){}

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.drawOval(10,10,10,10);
    }

    @Override
    public void move() {

    }
}