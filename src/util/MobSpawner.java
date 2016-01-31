package util;

import beings.badguys.Enemy;
import beings.badguys.Spider;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/31/2016
 * ******************************
 **/
public class MobSpawner {
    public Enemy spawnMob(int x, int y, int mobType){
        if(mobType==Enemy.SPIDER){
            return new Spider(x,y,2);
        }
        return null;
    }
}
