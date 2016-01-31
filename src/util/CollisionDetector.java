package util;

import beings.badguys.Enemy;
import beings.goodguys.Player;
import beings.goodguys.Summoner;
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

    public void detectPlayerEnemyCollision(Player p, LinkedList<Enemy> e) {
        Enemy enemy;
        for (int i=0; i<e.size(); i++) {
            enemy = e.get(i);
            if (checkCollision(p, enemy)) {
                p.takeDamage(1);
            }
        }
    }

    public void detectPlayerSummonerCollision(Player p, Summoner s) {
        if (checkCollision(p, s)) {
            p.heal(3);
        }
    }

    public boolean detectEnemyEnemyCollision(Enemy x, LinkedList<Enemy> enemies) {
        Enemy enemy;
        for (int i=0; i<enemies.size(); i++) {
            enemy = enemies.get(i);
            if (!x.equals(enemy)) {
                if (checkCollision(x, enemy)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void detectSpellCollision(LinkedList<Spell> s, LinkedList<Enemy> e) {
        Spell spell;
        Enemy enemy;
        for (int i=0; i<s.size(); i++) {
            spell=s.get(i);
            for (int j=0; j<e.size(); j++) {
                enemy = e.get(j);
                if (checkCollision(spell, enemy)) {
                    enemy.takeDamage(spell.getDamage());
                    spell.killSpell();
                }
            }
        }
    }

    public void detectSummonerCollision(Summoner s, LinkedList<Enemy> e) {
        Enemy enemy;
        for (int i=0; i<e.size(); i++) {
            enemy = e.get(i);
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
