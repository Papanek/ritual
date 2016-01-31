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
	private int maxSpeed = 2;
	private float SPEED = .15f;
	private float STOPSPEED = .1f;
	File img = new File("resource/wizardleftbigger.png");
	BufferedImage characterImage;
	private int teleportCooldown;
	private int flinchCooldown;
	public Player(int x, int y, int maxSpeed) {
		super(x, y, maxSpeed);
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
		super.drawHealthBar(health,g);
		g.drawImage(characterImage,0,0,null);
		g.translate(-x,-y);
	}

	@Override
	public void takeDamage(int damage){
		System.out.println("Player Health: " + health);
		if(flinchCooldown<=0){
			health-=damage;
			flinchCooldown=12;
		}

	}

	public void setUp(){
		if (movingUP) {
			speedUp += SPEED;
			if(speedUp>maxSpeed){
				speedUp=maxSpeed;
			}
		} else {
			if(speedUp != 0){
				speedUp -= STOPSPEED;
			}
			if(speedUp < 0){
				speedUp = 0;
			}
		}
	}

	public void setDown(){
		if (movingDown) {
			speedDown += SPEED;
			if(speedDown>maxSpeed){
				speedDown=maxSpeed;
			}
		} else {
			if(speedDown != 0){
				speedDown -= STOPSPEED;
			}
			if(speedDown < 0){
				speedDown = 0;
			}
		}
	}

	public void setLeft(){
		if (movingLeft) {
			speedLeft += SPEED;
			if(speedLeft>maxSpeed){
				speedLeft=maxSpeed;
			}
		} else {
			if(speedLeft != 0){
				speedLeft -= STOPSPEED;
			}
			if(speedLeft < 0){
				speedLeft = 0;
			}
		}
	}

	public void setRight(){
		if (movingRight) {
			speedRight += SPEED;
			if(speedRight>maxSpeed){
				speedRight=maxSpeed;
			}
		} else {
			if(speedRight != 0){
				speedRight -= STOPSPEED;
			}
			if(speedRight < 0){
				speedRight = 0;
			}
		}
	}


	@Override
	public void move() {
		setRight(); setUp(); setLeft(); setDown();
		this.y -= this.speedUp;
		this.y += this.speedDown;
		this.x -= this.speedLeft;
		this.x += this.speedRight;

		if(this.x>800-width){
			this.x = 800-width;
		} else if(this.x<0){
			this.x = 0;
		}
		if(this.y>600-height){
			this.y = 600-height;
		}
		if(this.y<0){
			this.y = 0;
		}

		teleportCooldown--;
		flinchCooldown--;
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

}