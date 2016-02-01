package effects;


import interfaces.Drawable;
import interfaces.Updatable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/

public abstract class Effect implements Drawable, Updatable {
    protected int timeTillRemove = 50;
    protected double x, y;
    protected String img;
    protected BufferedImage effectImage;
    public Effect(double x, double y, String imgLoc) {
        this.x = x;
        this.y = y;
        img = imgLoc;
        try {
            effectImage = ImageIO.read(this.getClass().getResourceAsStream(img));
        } catch (IOException e) {
            System.out.print("spellfuck");
        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.translate(x, y);
        g.drawImage(effectImage, 0, 0, null);
        g.translate(-x, -y);
    }

    @Override
    public void update() {
        timeTillRemove--;
    }

    public boolean isAlive(){
        return timeTillRemove>0;
    }

}
