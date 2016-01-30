package objects;

import interfaces.Controllable;
import interfaces.Movable;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * ******************************
 * Project: ritual
 * Creator: Daniel Papanek
 * Date :   1/29/2016
 * ******************************
 **/
public class Player extends Creature implements Movable, Controllable{
	List<Spell> spells = new LinkedList<>();
	boolean movingUP = false, movingDown = false, movingLeft = false, movingRight = false;
	private float SPEED = .1f;
	private float STOPSPEED = .35f;

	public Player(int x, int y, int health, int maxSpeed) {
		super(x, y, health, maxSpeed);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.translate(x,y);
		g.drawOval(0,0,100,100);
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
	}

	@Override
	public void moveUp(boolean keyPressed) {
		movingUP = keyPressed;
	}

	@Override
	public void moveDown(boolean keyPressed) {
		movingDown = keyPressed;
	}

	@Override
	public void moveLeft(boolean keyPressed) {
		movingLeft = keyPressed;
	}

	@Override
	public void moveRight(boolean keyPressed) {
		movingRight = keyPressed ;
	}
}