package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import java.io.Serializable;


public class Star extends Model implements Serializable {

	private static final long serialVersionUID = 5;

	private transient Polygon star;
	private Double[] xCoordinates, yCoordinates;
	private int sides;
	private double size, maxSize, minSize;
	int temp = 270;
	private boolean increasing = false;

	public void reinit(Unit u){
		Star s = (Star)u;
		Double[] finalList = new Double[s.sides];
		for (int i = 0; i < s.sides; i += 2) {
			finalList[i] = s.xCoordinates[i];
		}
		for (int i = 1; i < s.sides; i += 2) {
			finalList[i] = s.yCoordinates[i];
		}
		s.star = new Polygon();
		s.star.getPoints().addAll(finalList);
	}


	public Star(Manager manager, float yPosition, int sides, double size) {
		super(manager, yPosition);
		x = midPos;
		this.sides = sides;
		xCoordinates = new Double[sides];
		yCoordinates = new Double[sides];
		this.size = size;
		maxSize = 1.2 * size;
		minSize = 0.8 * size;
		for (int i = 0; i < sides; i++) {
			double angle = 90 + 360.0 / sides * i;
			if (i % 2 == 0) {
				xCoordinates[i] = (x + size * Math.cos(Math.toRadians(angle)));
				yCoordinates[i] = (yPosition + size * Math.sin(Math.toRadians(angle)));
			} else {
				xCoordinates[i] = (x + 0.5 * size * Math.cos(Math.toRadians(angle)));
				yCoordinates[i] = (yPosition + 0.5 * size * Math.sin(Math.toRadians(angle)));
			}
		}

		Double[] finalList = new Double[sides];
		for (int i = 0; i < sides; i += 2) {
			finalList[i] = xCoordinates[i];
		}
		for (int i = 1; i < sides; i += 2) {
			finalList[i] = yCoordinates[i];
		}
		star = new Polygon();
		star.getPoints().addAll(finalList);
	}

	@Override
	public void clock() {

	}

	@Override
	public void render(GraphicsContext g) {
		temp %= 360;
		g.setFill(Color.WHITE);

		double[] xCoordinates1 = new double[sides];
		double[] yCoordinates1 = new double[sides];
		for (int i = 0; i < sides; i++) {
			xCoordinates1[i] = xCoordinates[i];
			yCoordinates1[i] = yCoordinates[i];
		}

		if (size > maxSize) {
			increasing = false;
		}
		if (size < minSize) {
			increasing = true;
		}
		if (increasing) {
			size *= 1.005;
		} else {
			size *= 0.995;
		}

		for (int i = 0; i < sides; i++) {
			double angle = temp + 360.0 / sides * i;
			if (i % 2 == 0) {
				xCoordinates1[i] = (int) (x + size * Math.cos(Math.toRadians(angle)));
				yCoordinates1[i] = (int) (yPosition - manager.getGameCamera().getyOffset() + size * Math.sin(Math.toRadians(angle)));
			} else {
				xCoordinates1[i] = (int) (x + 0.5 * size * Math.cos(Math.toRadians(angle)));
				yCoordinates1[i] = (int) (yPosition - manager.getGameCamera().getyOffset() + 0.5 * size * Math.sin(Math.toRadians(angle)));
			}
		}

		star = new Polygon();
		Double[] finalList = new Double[sides];
		for (int i = 0; i < sides; i += 2) {
			finalList[i] = xCoordinates1[i];
		}
		for (int i = 1; i < sides; i += 2) {
			finalList[i] = yCoordinates1[i];
		}

		star.getPoints().addAll(finalList);
		g.setFill(Color.WHITE);
		g.fillPolygon(xCoordinates1, yCoordinates1, sides);

	}


	@Override
	public boolean collidesWith(Ellipse body, int color) {
		Shape shape = Shape.intersect(body,star);

		if (shape.getBoundsInLocal().getWidth()!=-1) {
				return true;
		}
		return false;
	}
}
