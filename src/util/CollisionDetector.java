package util;

import beings.Enemy;
import beings.Player;
import beings.Summoner;
import interfaces.Collidable;
import magic.Spell;

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

    public void detectPlayerEnemyCollision(Player p, LinkedList<Enemy> e) {
        for (Enemy enemy : e) {
            if (checkCollision(p, enemy)) {
                p.takeDamage(2);
            }
        }
    }

    public void detectPlayerSummonerCollision(Player p, Summoner s) {
        if (checkCollision(p, s)) {
            p.heal(2);
        }
    }

    public boolean detectEnemyEnemyCollision(Enemy x, LinkedList<Enemy> enemies) {
        for (Enemy e : enemies) {
            if (!x.equals(e)) {
                if (checkCollision(x, e)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void detectSpellCollision(LinkedList<Spell> s, LinkedList<Enemy> e) {
        for (Spell spell : s) {
            for (Enemy enemy : e) {
                if (checkCollision(spell, enemy)) {
                    enemy.takeDamage(spell.getDamage());
                    spell.killSpell();
                }
            }
        }
    }

    public void detectSummonerCollision(Summoner s, LinkedList<Enemy> e) {
        for (Enemy enemy : e) {
            if (checkCollision(enemy, s)) {
                s.takeDamage(2);
            }
        }
    }

    private boolean checkCollision(Collidable object1, Collidable object2) {
        boolean horizontal = false, vertical = false;
        if (object1.getX() < object2.getX() + object2.getWidth() && object1.getX() + object1.getWidth() > object2.getX()) {
            horizontal = true;
        }
        if (object1.getY() < object2.getY() + object2.getWidth() && object1.getY() + object1.getHeight() > object2.getY()) {
            vertical = true;
        }
        return (horizontal && vertical);
    }
}
