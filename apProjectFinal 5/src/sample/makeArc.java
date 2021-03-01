package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.StrokeType;

import java.awt.*;
import java.io.Serializable;

public class makeArc implements Serializable {

	private static final long serialVersionUID = 28;
	private final int radius;
	private final int colorType;
	private double startAngle;

	private transient Arc arc;

	private final float x;
	private final float yPosition;
	private final Manager manager;
	private final float thickness;
	private static boolean isMenu= false;

	public makeArc(Manager manager, float yPosition, int radius, double startAngle, int color, int ypos) {
		this.manager = manager;
		this.x = manager.getWidth() / 2 - radius/2;
		this.yPosition = yPosition;
		this.radius = radius;
		this.startAngle = startAngle;
		this.colorType = color;
		this.thickness = 20;
		//this.ypos = ypos;
		arc = new Arc(x, yPosition, radius, radius, startAngle, 90);
		arc.setFill(null);
		arc.setType(ArcType.OPEN);
		arc.setStroke(Unit.colors[colorType]);
		arc.setStrokeWidth(thickness);
	}

	public void reinit(makeArc m){
		m.manager.reinit(m.manager);
		m.arc = new Arc(x, yPosition, radius, radius, startAngle, 90);
		m.arc.setFill(null);
		m.arc.setType(ArcType.OPEN);
		m.arc.setStroke(Unit.colors[colorType]);
		m.arc.setStrokeWidth(thickness);
	}

	public void render(GraphicsContext g) {
		int xPos = (int) x;
		//int yPos;

		int ypos;
		if(!isMenu){
			ypos = (int) (yPosition - manager.getGameCamera().getyOffset());}
		else{
			ypos = (int) (yPosition);}

		arc.setCenterX(xPos);
		arc.setCenterY(ypos);

		arc.setRadiusX(radius);
		arc.setRadiusY(radius);
		arc.setStartAngle(startAngle);
		g.setLineWidth(thickness);
		g.setFill(Unit.colors[colorType]);
		g.setStroke(Unit.colors[colorType]);
		g.strokeArc(xPos, ypos, radius, radius, startAngle, 90, ArcType.OPEN);
	}

	public void setStartAngle(Double a){
		startAngle = a;
	}

	public int getColorType() {
		return colorType;
	}

	public Arc getArc(){ return arc;}

	public boolean getIsMenu(){return isMenu;}

	public static void setIsMenu(boolean menu1){
		isMenu = menu1;
	}
}
