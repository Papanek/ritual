package magic.projectiles;

import magic.Spell;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/31/2016
 * ******************************
 **/
public abstract class Projectile extends Spell {
    public Projectile(int x, int y, double mouseX, double mouseY, String imgLoc) {
        super(imgLoc);
        this.x = x;
        this.y = y;
        speedX = (float) (mouseX - this.x);
        speedY = (float) (mouseY - this.y);
        double theta = Math.atan2(speedY, speedX);
        speedX = (float) Math.cos(theta) * 6;
        speedY = (float) Math.sin(theta) * 6;
    }
    @Override
    public void update() {
        move();
    }
}