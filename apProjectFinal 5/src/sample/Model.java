package sample;

import java.io.Serializable;

public abstract class Model extends Unit implements Serializable {

	private static final long serialVersionUID = 11;
	private static final float DEFAULT_SPEED = 5.0f;
	protected float xMove, yMove, speed, fall;
	protected int color = 1;

	public void reinit(Model i){
		//nothing
	}

	public Model(Manager manager, float yPosition) {
		super(manager, yPosition);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	public void move() {
		if(manager.getMouseManager().getLeftPressed())
			moveY();
		fall();
	}

	public void moveY() {
		yPosition += yMove;
	}

	public void fall() {
		yPosition += fall;
	}

	// Getters & Setters

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
