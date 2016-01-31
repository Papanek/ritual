package magic.target;

import magic.Spell;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/31/2016
 * ******************************
 **/
public abstract class TargetSpell extends Spell {
    public TargetSpell(double mouseX, double mouseY, String imgLoc) {
        super(imgLoc);
        this.x = (int) mouseX;
        this.y = (int) mouseY;
        damage = 0;
    }
}
