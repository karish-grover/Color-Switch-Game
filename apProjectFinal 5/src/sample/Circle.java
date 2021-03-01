package sample;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.ArrayList;

public class Circle extends Obstacle implements Serializable {
	private static final long serialVersionUID = 29;
	private static final int DEFAULT_CIRCLE_DIAMETER = 200;
	private static final int DEFAULT_CIRCLE_THICKNESS = 15;
	private static final int DEFAULT_CIRCLE_ANGULARSPEED = 1;
	
	private int diameter = DEFAULT_CIRCLE_DIAMETER;
	private int angularSpeed = DEFAULT_CIRCLE_ANGULARSPEED;
	private int thickness;
	private double currentAngle;
	private int ypos=0;

	private ArrayList<makeArc> lesArc;

	public void reinit(Unit c1){
		Circle c= (Circle)c1;

		System.out.println("circle init called");

		for (int i = 0; i < c.lesArc.size() ; i++) {
			c.lesArc.get(i).reinit(c.lesArc.get(i));
		}
	}
	
	public Circle(Manager handler, float y, int diameter, int angularSpeed, int ypos) {

		super(handler, y - diameter / 2);
		x = midPos;
		this.diameter = diameter;
		this.thickness = DEFAULT_CIRCLE_THICKNESS;
		this.currentAngle = 0;
		this.angularSpeed = angularSpeed;
		this.ypos = ypos;

		lesArc = new ArrayList<makeArc>();

		lesArc.add(new makeArc(handler, yPosition, diameter, currentAngle, 0,ypos));
		lesArc.add(new makeArc(handler, yPosition, diameter, currentAngle + 90, 1,ypos));
		lesArc.add(new makeArc(handler, yPosition, diameter, currentAngle + 180, 2,ypos));
		lesArc.add(new makeArc(handler, yPosition, diameter, currentAngle + 270, 3,ypos));
	}

	public void updateAngle() {
		currentAngle = (currentAngle + angularSpeed) % 360;
	}	

	@Override
	public void clock() {
		updateAngle();
	}
	
	@Override
	public void render(GraphicsContext g) {

		lesArc.get(0).setStartAngle(currentAngle);
		lesArc.get(1).setStartAngle(currentAngle + 90);
		lesArc.get(2).setStartAngle(currentAngle + 180);
		lesArc.get(3).setStartAngle(currentAngle + 270);
		
		for (int i = 0; i < lesArc.size(); i++) {
			lesArc.get(i).render(g);
		}
	}

	@Override
	public boolean collidesWith(Ellipse body, int bodycolor) {
		System.out.println("circle collides with");
//		for (int i = 0; i < lesArc.size(); i++) {
//			Shape shape = Shape.intersect(body, lesArc.get(i).getArc());
//
//			System.out.println(body);
//			System.out.println(lesArc.get(i).getArc());
//
//			System.out.println(Unit.colors[bodycolor]+" "+ lesArc.get(i).getArc().getStroke());
//
//			if (shape.getBoundsInLocal().getWidth() != -1.0) {
//				System.out.println("collision detected in circle");
//				if (Unit.colors[bodycolor] != lesArc.get(i).getArc().getStroke()) return true;
//			}
//		}

		return false;
	}

	// Getters and Setters

	public int getAngularSpeed() {
		return angularSpeed;
	}

	public void setAngularSpeed(int angularSpeed) {
		this.angularSpeed = angularSpeed;
	}

	public double getCurrentAngle() {
		return currentAngle;
	}

	public void setCurrentAngle(double currentAngle) {
		this.currentAngle = currentAngle;
	}
}
