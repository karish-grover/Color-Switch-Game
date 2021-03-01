package sample;

import java.io.Serializable;

public abstract class Obstacle extends Unit implements Serializable {

	private static final long serialVersionUID = 9;

	private static final float DEFAULT_SPEED = 3f;

	protected float xMove, yMove, speed;
	
	public Obstacle(Manager manager, float yPosition) {
		super(manager, yPosition);
		xMove = 0;
		yMove = 0;
		speed = DEFAULT_SPEED;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

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
}
