package interfaces;

import java.awt.*;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/30/2016
 * ******************************
 **/
public interface Destructable {
    void takeDamage(int damage);
    void drawHealthBar(int health,Graphics2D g);
    boolean isAlive();
}
