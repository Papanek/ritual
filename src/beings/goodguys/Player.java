package beings.goodguys;

import beings.Humanoid;
import interfaces.Controllable;
import magic.Spell;
import magic.projectiles.Fireball;
import magic.target.Teleport;

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
public class Player extends Humanoid implements Controllable {
    private final String PLAYER_LEFT = "/images/wizardleftbigger.png";
    private final String PLAYER_RIGHT = "/images/wizardrightbigger.png";
    private final String PLAYER_UP = "/images/wizardup.png";
    private final String PLAYER_DOWN = "/images/wizarddown.png";

    private boolean movingUP = false, movingDown = false, movingLeft = false, movingRight = false;
    private float speedUp = 0, speedDown = 0, speedLeft = 0, speedRight = 0;


    private int maxSpeed = 2;
    private float SPEED = .15f;
    private float STOPSPEED = .1f;
    String img;
    BufferedImage characterImage;

    private int flinchCooldown;
    private int healCooldown;

    private int currentPrimarySpell;
    private int currentPrimarySpellCooldown = 0;
    private int currentSecondarySpell;
    private int currentSecondarySpellCooldown = 0;

    public Player(int x, int y, int maxSpeed) {
        super(x, y, maxSpeed);
        currentPrimarySpell = Spell.FIREBALL;
        currentSecondarySpell = Spell.TELEPORT;

        img = PLAYER_RIGHT;
        height = 50;
        width = 50;
        try {
            characterImage = ImageIO.read(this.getClass().getResourceAsStream(img));
        } catch (IOException e) {
            System.out.print("playerfuck");
        }
    }

    @Override
    public void update() {
        move();
        updateImage();
        currentPrimarySpellCooldown--;
        currentSecondarySpellCooldown--;
        flinchCooldown--;
        healCooldown--;
    }

    @Override
    public void draw(Graphics2D g) {
        g.translate(x, y);
        super.drawHealthBar(health, g);
        g.drawImage(characterImage, 0, 0, null);
        g.translate(-x, -y);
    }

    @Override
    public void takeDamage(int damage) {
        System.out.println("Player Health: " + health);
        if (flinchCooldown <= 0) {
            health -= damage;
            flinchCooldown = 12;
        }
        if (health <= 0) {
            alive = false;
        }

    }

    public void heal(int boost) {
        if (health < maxHealth && healCooldown <= 0) {
            health += boost;
            healCooldown = 5;
        }
    }

    private float set(boolean dir, float dirSpeed) {
        if (dir) {
            dirSpeed += SPEED;
            if (dirSpeed > maxSpeed) {
                dirSpeed = maxSpeed;
            }
        } else {
            if (dirSpeed != 0) {
                dirSpeed -= STOPSPEED;
            }
            if (dirSpeed < 0) {
                dirSpeed = 0;
            }
        }
        return dirSpeed;
    }

    public void move() {
        speedRight = set(movingRight, speedRight);
        speedUp = set(movingUP, speedUp);
        speedLeft = set(movingLeft, speedLeft);
        speedDown = set(movingDown, speedDown);
        this.y -= this.speedUp;
        this.y += this.speedDown;
        this.x -= this.speedLeft;
        this.x += this.speedRight;

        if (this.x > 800 - width) {
            this.x = 800 - width;
        } else if (this.x < 0) {
            this.x = 0;
        }
        if (this.y > 600 - height) {
            this.y = 600 - height;
        }
        if (this.y < 0) {
            this.y = 0;
        }
    }

    private void updateImage() {
        if (speedRight > 0) {
            img = PLAYER_RIGHT;
        } else if (speedLeft > 0) {
            img = PLAYER_LEFT;
        } else if (speedUp > 0) {
            img = PLAYER_UP;
        } else if (speedDown > 0) {
            img = PLAYER_DOWN;
        }
        try {
            characterImage = ImageIO.read(this.getClass().getResourceAsStream(img));
        } catch (IOException e) {
            System.out.print("playerfuck");
        }
    }

    public void move(char dir, boolean b) {
        switch (dir) {
            case 'U':
                movingUP = b;
                break;
            case 'D':
                movingDown = b;
                break;
            case 'L':
                movingLeft = b;
                break;
            case 'R':
                movingRight = b;
                break;
        }

    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Takes the x and y coordinates of the mouse when clicked and creates a new spell with a speedX and speedY towards the mouse
     * starting at the player's position
     *
     * @param mouseX mouse x
     * @param mouseY mouse y
     */
    public Spell castPrimarySpell(int mouseX, int mouseY) {
        if (currentPrimarySpellCooldown <= 0) {

            if (currentPrimarySpell == Spell.FIREBALL) {
                currentPrimarySpellCooldown = 50;
                return new Fireball((int) x, (int) y, mouseX, mouseY);
            }

        }
        return null;
    }

    public Spell castSecondarySpell(int mouseX, int mouseY) {
        if (currentSecondarySpellCooldown <= 0) {

            if (currentSecondarySpell == Spell.TELEPORT) {
                currentSecondarySpellCooldown = 500;
                return new Teleport(this, mouseX, mouseY);
            }

        }
        return null;
    }

}