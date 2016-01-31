package util;

import interfaces.Updatable;

/**
 * Created by brewi on 2016-01-31.
 */
public class ScoreKeeper implements Updatable {
    private int score;
    private long startTime;

    public ScoreKeeper(){
        score=0;
    }

    public void addScore(int plus){
        score = score + plus;
    }

    public int getScore(){
        return score;
    }

    @Override
    public void update() {
        if(startTime<=0){
            startTime=System.currentTimeMillis();
        }
        else if((System.currentTimeMillis()-startTime) > 1000){
            addScore(1);
            startTime=0;
            System.out.println("adding to score");
        }
    }
}
