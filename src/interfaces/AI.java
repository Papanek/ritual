package interfaces;

import beings.goodguys.Player;
import beings.goodguys.Summoner;

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
