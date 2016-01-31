package interfaces;

import beings.Player;
import beings.Summoner;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/30/2016
 * ******************************
 **/
public interface AI {
    void moveTo(Player p, Summoner s);
    void moveAway();
}
