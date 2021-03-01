package sample;
import javafx.geometry.Bounds;
import com.sun.javafx.geom.Area;
import com.sun.javafx.geom.PathIterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;

import java.io.Serializable;

public class ColorChangeBall extends Model implements Serializable {
	private static final long serialVersionUID = 21;
	private static final int DEFAULT_DIAMETER = 20;
	transient private Ellipse switcherBody;
	
	public ColorChangeBall(Manager manager, float yPosition) {
		super(manager, yPosition);
		switcherBody = new Ellipse(x - DEFAULT_DIAMETER / 2, yPosition, DEFAULT_DIAMETER, DEFAULT_DIAMETER);
	}
	
	@Override
	public void clock() {
		
	}

	public void reinit(Unit u){
		ColorChangeBall colorChangeBall= (ColorChangeBall)u;
		colorChangeBall.switcherBody = new Ellipse(x - DEFAULT_DIAMETER / 2, yPosition, DEFAULT_DIAMETER, DEFAULT_DIAMETER);
	}

	@Override
	public void render(GraphicsContext g) {
		int xPos = (int) midPos - DEFAULT_DIAMETER/2;
		int yPos = (int) (yPosition - manager.getGameCamera().getyOffset() + 100); // test

		g.setFill(Unit.colors[0]);
		g.fillArc(xPos, yPos, DEFAULT_DIAMETER, DEFAULT_DIAMETER, 0, 90, ArcType.ROUND);
		g.setFill(Unit.colors[1]);
		g.fillArc(xPos, yPos, DEFAULT_DIAMETER, DEFAULT_DIAMETER, 90, 90, ArcType.ROUND);
		g.setFill(Unit.colors[2]);
		g.fillArc(xPos, yPos, DEFAULT_DIAMETER, DEFAULT_DIAMETER, 180, 90, ArcType.ROUND);
		g.setFill(Unit.colors[3]);
		g.fillArc(xPos, yPos, DEFAULT_DIAMETER, DEFAULT_DIAMETER, 270, 90, ArcType.ROUND);
		
		switcherBody.setCenterX(xPos);
		switcherBody.setCenterY(yPos);//CHANGE HAPPENED
		switcherBody.setRadiusX(DEFAULT_DIAMETER);
		switcherBody.setRadiusY(DEFAULT_DIAMETER);
	}
	
	@Override
	public boolean collidesWith(Ellipse body, int color) {
		return body.getBoundsInLocal().intersects(switcherBody.getBoundsInLocal());
	}
}
