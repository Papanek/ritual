package scoring;

import interfaces.Updatable;

import java.util.ArrayList;

/**
 * Created by brewi on 2016-01-31.
 */
public class HighScore implements Updatable{
    ArrayList<Score> scores;
    private int highScore;

    public HighScore (){
        scores = new ArrayList<>();
        highScore = 0;
    }

    public int getHighScore(){
        return highScore;
    }

    public void addScore(Score s){
        scores.add(s);
    }

    @Override
    public void update() {
        for (Score score:scores) {
            if(highScore< score.getScore()){
                highScore = score.getScore();
            }
        }
    }
}
