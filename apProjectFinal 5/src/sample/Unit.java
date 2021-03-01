package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

import java.io.Serializable;

public abstract class Unit implements Serializable {
	private static final long serialVersionUID = 3;
	protected Manager manager;
	protected float x, yPosition;
	protected float midPos;

    public static final Color[] colors = {Color.rgb(50, 226, 241),
        Color.rgb(244, 222, 14), Color.rgb(140, 18, 251), Color.rgb(255, 0, 128)};
	
	public Unit(Manager manager, float yPosition) {
		this.manager = manager;
		this.yPosition = yPosition;
		this.midPos = manager.getWidth() / 2;
		this.x = midPos;
	}

	// Abstract methods for the children
	
	public abstract void clock();
	public abstract void render(GraphicsContext g);
	public abstract boolean collidesWith(Ellipse body, int bodycolor);// is used for collision detection
	public abstract void reinit(Unit u);//method to re-initialize


	// Getters & Setters
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getyPosition() {
		return yPosition;
	}

	public void setyPosition(float yPosition) {
		this.yPosition = yPosition;
	}

	public static Color[] getColors() {
		return colors;
	}

}
