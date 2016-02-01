package magic.target;

import beings.goodguys.Player;
import effects.Effect;
import effects.TeleportEffect;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/31/2016
 * ******************************
 **/
public class Teleport extends TargetSpell {
    public Teleport(Player p, double mouseX, double mouseY) {
        super(mouseX, mouseY, "resource/teleport.png");
        super.effect = new TeleportEffect(p.getX(),p.getY());
        p.setX(mouseX);
        p.setY(mouseY);
        System.out.print("Tele");
    }

    @Override
    public void update() {
        timeToRemove--;
        if(timeToRemove<0){
            killSpell();
        }
    }
}
