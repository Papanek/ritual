package scoring;

import interfaces.Updatable;

/**
 * Created by brewi on 2016-01-31.
 */
public class Timer implements Updatable {
    private int time;
    private long startTime;

    public Timer(){
        time=0;
    }

    public void addTime(int second){
        time = time + second;
    }

    public int getTimer(){
        return time;
    }

    @Override
    public void update() {
        if(startTime<=0){
            startTime=System.currentTimeMillis();
        }
        else if((System.currentTimeMillis()-startTime) > 1000){
            addTime(1);
            startTime=0;
            System.out.println("adding to second");
        }
    }
}
