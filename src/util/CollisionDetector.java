package util;

import interfaces.Collidable;
import beings.Enemy;
import beings.Player;
import magic.Spell;
import beings.Summoner;

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
    public void detectPlayerEnemyCollision(Player p, LinkedList<Enemy> e){
        for(Enemy enemy : e ){
            if(checkCollision(p,enemy)){
                p.takeDamage(1);
                System.out.println("Im Hurt");
            }
        }
    }

    public boolean detectEnemyEnemyCollision(Enemy x, LinkedList<Enemy> enemies){
        for(Enemy e : enemies){
            if(!x.equals(e)) {
                if (checkCollision(x, e)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void detectSpellCollision(LinkedList<Spell> s, LinkedList<Enemy> e){
        for(Spell spell : s ){
            for(Enemy enemy : e) {
                if(checkCollision(spell,enemy)){
                    enemy.takeDamage(spell.getDamage());
                    spell.killSpell();
                }
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

    private boolean checkCollision(Collidable object1, Collidable object2){
        boolean horizontal = false, vertical = false;
        if(object1.getX() < object2.getX()+object2.getWidth() && object1.getX()+object1.getWidth() > object2.getX()){
            horizontal = true;
        }
        if(object1.getY()<object2.getY()+object2.getWidth()&&object1.getY()+object1.getHeight()>object2.getY()){
            vertical=true;
        }
        return (horizontal&&vertical);
    }
}
