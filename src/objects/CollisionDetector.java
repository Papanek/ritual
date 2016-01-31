package objects;

import interfaces.Collidable;

import java.util.LinkedList;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papaneks
 * Date :   1/29/2016
 * ******************************
 **/
public class CollisionDetector {

    boolean flinching = false;
    public void detectPlayerCollision(Player p, LinkedList<Enemy> e){
        for(Enemy enemy : e ){
            if(checkCollision(p,enemy)){
                System.out.println("Im Hurt");
            }
        }
    }



    public void detectSummonerCollision(Summoner s, LinkedList<Enemy> e){
        if(!flinching){
            //for(Enemy enemy : e ){
                //if(sx > ex && sx < ex+ew){
                    //if(sy > ey && sy < ey+eh){
                        //summoner set flinching
                        //summoner take damage
                        //summoner spell??
                       // break;
                    //}
                //}
            //}
        }
    }

    public void detectSpellCollision(LinkedList<Spell> s, LinkedList<Enemy> e){
        for(Spell spell : s ){
            for(Enemy enemy : e) {
                //if (sx > ex && sx < ex + ew) {
                   // if (sy > ey && sy < ey + eh) {
                        //remove spell
                        //hurt enemy
                      //  break;
                  // }
               // }
            }
        }
    }

    private boolean checkCollision(Collidable object1, Collidable object2){
        boolean horizontal = false, vertical = false;
        if(object1.getX() < object2.getX()+30 && object1.getX()+100 > object2.getX()){
            horizontal = true;
        }
        if(object1.getY()<object2.getY()+30&&object1.getY()+100>object2.getY()){
            vertical=true;
        }
        return (horizontal&&vertical);
    }
}
