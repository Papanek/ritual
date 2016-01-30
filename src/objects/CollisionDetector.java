package objects;

import java.util.LinkedList;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class CollisionDetector {
    private int pw, ew, sw;
    private int ph, eh, sh;
    int px, ex, sx;
    int py, ey, sy;
    boolean flinching;
    public void detectPlayerCollision(Player p, LinkedList<Enemy> e){
        if(!flinching){
            for(Enemy enemy : e ){
                if(px > ex && px < ex+ew){
                    if(py > ey && py < ey+eh){
                        //player take damage
                        //player set flinching
                        break;
                    }
                }
            }
        }
    }

    public void detectSummonerCollision(Summoner s, LinkedList<Enemy> e){
        if(!flinching){
            for(Enemy enemy : e ){
                if(sx > ex && sx < ex+ew){
                    if(sy > ey && sy < ey+eh){
                        //summoner set flinching
                        //summoner take damage
                        //summoner spell??
                        break;
                    }
                }
            }
        }
    }

    public void detectSpellCollision(LinkedList<Spell> s, LinkedList<Enemy> e){
        for(Spell spell : s ){
            for(Enemy enemy : e) {
                if (sx > ex && sx < ex + ew) {
                    if (sy > ey && sy < ey + eh) {
                        //remove spell
                        //hurt enemy
                        break;
                    }
                }
            }
        }
    }
}
