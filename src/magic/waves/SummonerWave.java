package magic.waves;

import interfaces.Collidable;

/**
 * Created by brewi on 2016-01-31.
 */
public class SummonerWave extends Wave {

    public SummonerWave() {
        super("resource/summonerwave.png");
        this.x = 300;
        this.y = 200;
        this.width = 200;
        this.height = 200;
        enemiesHit = 5;
    }

    @Override
    public void update() {
        timeToRemove--;
        if(timeToRemove<0){
            killSpell();
        }
    }
}
