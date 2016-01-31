package util;

import beings.badguys.Enemy;
import beings.badguys.Spider;

import java.util.Random;


/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/31/2016
 * ******************************
 **/
public class MobSpawner {
    private long start=0,stop=0;
    private int difficultyScale = 1;
    private int spawnIncrement = 5000;
    private Random rand = new Random();
    public MobSpawner(){}
    public Enemy[] spawnEndless(){
        int enemies = (int)(difficultyScale*.75);
        Enemy[] e = new Enemy[enemies];
        if(start==0){
            start = System.currentTimeMillis();
        } else if(System.currentTimeMillis()-start>spawnIncrement){
            System.out.println("Adding enemies");
            int x=1,y=1;
            for(int i=0; i<enemies; i++){
                if(i%2==0){
                    x=(int)(-800*rand.nextFloat());
                } else {
                    x=800+(int)(800*rand.nextFloat());
                }
                if(i%3==1){
                    y=(int)(-600*rand.nextFloat());
                } else {
                    y=600+(int)(600*rand.nextFloat());
                }
                System.out.println(x+" "+y);
                e[i]=new Spider(x,y,0.5f);
            }
            difficultyScale++;
            start=0;
        }
        return e;
    }
}