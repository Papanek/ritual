package beings;

import interfaces.Controllable;
import interfaces.Destructable;
import interfaces.Movable;
import magic.Spell;

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
public class Player extends Humanoid implements Movable, Controllable{
	boolean movingUP = false, movingDown = false, movingLeft = false, movingRight = false;
	private float SPEED = .1f;
	private float STOPSPEED = .35f;
	File img = new File("resource/wizardleftbigger.png");
	BufferedImage characterImage;
	private int teleportCooldown;
	public Player(int x, int y, int health, int maxSpeed) {
		super(x, y, health, maxSpeed);
		height = 50;
		width  = 50;
		try{
			characterImage= ImageIO.read(img);
		}
		catch(IOException e){System.out.print("fuck");}
	}

	@Override
	public void draw(Graphics2D g) {
		//g.setColor(Color.black);
		g.translate(x,y);
		g.drawImage(characterImage,0,0,null);
		g.translate(-x,-y);
	}

	public void setUp(){
		if(!movingUP){
			if(speedUp != 0){
				speedUp -= STOPSPEED;
			}
			if(speedUp < 0){
				speedUp = 0;
			}
			if(speedUp == 0){
				movingUP = false;
			}
		}else{
			speedUp += SPEED;
		}
	}

	public void setDown(){
		if(!movingDown){
			if(speedDown != 0){
				if(speedDown > 0){
					speedDown -= STOPSPEED;
				}
				if(speedDown < 0){
					speedDown = 0;
				}
			}
			if(speedDown == 0){
				movingDown = false;
			}
		}else{
			speedDown += SPEED;
		}
	}

	public void setLeft(){
		if(!movingLeft){
			if (speedLeft != 0){
				speedLeft -= STOPSPEED;
			}
			if(speedLeft < 0){
				speedLeft = 0;
			}
			if(speedLeft == 0){
				movingLeft = false;
			}
		}else{
			speedLeft += SPEED;
		}
	}

	public void setRight(){
		if(!movingRight){
			if(speedRight!= 0){
				speedRight-= STOPSPEED;
			}
			if(speedRight< 0){
				speedRight = 0;
			}
			if(speedRight == 0){
				movingRight = false;
			}
		}else{
			speedRight += SPEED;
		}
	}


	@Override
	public void move() {
		setRight(); setUp(); setLeft(); setDown();
		if(movingUP)this.y -= this.speedUp;
		if(movingDown)this.y += this.speedDown;
		if(movingLeft)this.x -= this.speedLeft;
		if(movingRight)this.x += this.speedRight;
		teleportCooldown--;
	}

	@Override
	public void moveUp(boolean keyPressed) {
		movingUP = keyPressed;
		if(movingUP){
			img = new File("resource/wizardupdownbigger.png");
			try{
				characterImage= ImageIO.read(img);
			}
			catch(IOException e){System.out.print("fuck");}
		}
	}

	@Override
	public void moveDown(boolean keyPressed) {
		movingDown = keyPressed;
		if(movingDown){
			img = new File("resource/wizardupdownbigger.png");
			try{
				characterImage= ImageIO.read(img);
			}
			catch(IOException e){System.out.print("fuck");}
		}
	}

	@Override
	public void moveLeft(boolean keyPressed) {
		movingLeft = keyPressed;
		if(movingLeft){
			img = new File("resource/wizardleftbigger.png");
			try{
				characterImage= ImageIO.read(img);
			}
			catch(IOException e){System.out.print("fuck");}
		}
	}

	@Override
	public void moveRight(boolean keyPressed) {
		movingRight = keyPressed ;
		if(movingRight){
			img = new File("resource/wizardrightbigger.png");
			try{
				characterImage= ImageIO.read(img);
			}
			catch(IOException e){System.out.print("fuck");}
		}
	}

	/**
	 * Takes the x and y coordinates of the mouse when clicked and creates a new spell with a speedX and speedY towards the mouse
	 * starting at the player's position
	 *
	 * @param mouseX mouse x
	 * @param mouseY mouse y
	 */
	public Spell castSpell(int mouseX, int mouseY){

		return new Spell((int)x,(int)y,mouseX, mouseY, Spell.FIREBALL);


	}

	public void teleport(int mouseX, int mouseY){
		if(teleportCooldown <= 0){
			this.x = mouseX;
			this.y = mouseY;
			this.teleportCooldown = 500;
		}
	}

	@Override
	public boolean isAlive() {
		return alive;
	}
}