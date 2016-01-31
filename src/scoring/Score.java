package scoring;

import interfaces.Updatable;

/**
 * Created by brewi on 2016-01-31.
 */
public class Score implements Updatable{
    private int enemiesKilled;
    private int score;
    private Timer timer;

    public Score(Timer timer){
        score = 0;
        enemiesKilled = 0;
        this.timer = timer;
    }

    public void killedEnemy(){
        enemiesKilled++;
    }

    public int computeScore(){
        score = enemiesKilled * timer.getTimer();
        return score;
    }

    public int getScore(){
        return score;
    }

    public int getEnemiesKilled(){
        return enemiesKilled;
    }

    @Override
    public void update() {
        computeScore();

    }
}
