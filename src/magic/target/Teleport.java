package magic.target;

import beings.Player;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/31/2016
 * ******************************
 **/
public class Teleport extends TargetSpell {
    public Teleport(Player p, double mouseX, double mouseY) {
        super(mouseX, mouseY, "resource/summonerbigger.png");
        p.setX(mouseX);
        p.setY(mouseY);
        System.out.print("Tele");
        health = 50;
    }

    @Override
    public void update() {
        health--;
        if(health<0){
            killSpell();
        }
    }
}
