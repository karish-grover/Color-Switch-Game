package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.io.Serializable;
import java.util.ArrayList;

public class Rectangle extends Obstacle implements Serializable {
	private final int speed;
	private static final long serialVersionUID = 24;
	private int xsline, xsline2, xsline3, xsline4;
	private int xeline, xeline2, xeline3, xeline4;
	private int Sxsline, Sxsline2, Sxsline3, Sxsline4;
	private int Sxeline, Sxeline2, Sxeline3, Sxeline4;

	private float y;
	private static int width = 500;

	transient private Line line, line2, line3, line4, Sline, Sline2, Sline3, Sline4;
	transient private ArrayList<Line> lines = new ArrayList<Line>();

	public void reinit(Unit u){
		Rectangle r = (Rectangle) u;
		System.out.println("rectangle init called");
		r.lines = new ArrayList<Line>();

		r.lines.add(line = new Line());
		r.lines.add(line2 = new Line());
		r.lines.add(line3 = new Line());
		r.lines.add(line4 = new Line());
		r.lines.add(Sline = new Line());
		r.lines.add(Sline2 = new Line());
		r.lines.add(Sline3 = new Line());
		r.lines.add(Sline4 = new Line());
	}

	public Rectangle(Manager manager, float yPosition, int speed) {
		super(manager, yPosition);
		this.speed = speed;
		y = yPosition - manager.getGameCamera().getyOffset();
		initialisation();
		lines.add(line = new Line());
		lines.add(line2 = new Line());
		lines.add(line3 = new Line());
		lines.add(line4 = new Line());

		lines.add(Sline = new Line());
		lines.add(Sline2 = new Line());
		lines.add(Sline3 = new Line());
		lines.add(Sline4 = new Line());

	}

	private void initialisation() {

		xsline = 10;
		xeline = 115;

		xsline2 = 135;
		xeline2 = 240;

		xsline3 = 260;
		xeline3 = 365;

		xsline4 = 385;
		xeline4 = 490;

		Sxsline = xsline + width;
		Sxeline = xeline + width;
		Sxsline2 = xsline2 + width;
		Sxeline2 = xeline2 + width;
		Sxsline3 = xsline3 + width;
		Sxeline3 = xeline3 + width;
		Sxsline4 = xsline4 + width;
		Sxeline4 = xeline4 + width;

	}

	@Override
	public void clock() {

		xsline -= speed;
		xeline -= speed;
		xsline2 -= speed;
		xeline2 -= speed;
		xsline3 -= speed;
		xeline3 -= speed;
		xsline4 -= speed;
		xeline4 -= speed;

		Sxsline -= speed;
		Sxeline -= speed;
		Sxsline2 -= speed;
		Sxeline2 -= speed;
		Sxsline3 -= speed;
		Sxeline3 -= speed;
		Sxsline4 -= speed;
		Sxeline4 -= speed;

		if (Sxeline4 == 500) {
			initialisation();
		}
	}

	@Override
	public void render(GraphicsContext g) {

		g.setLineWidth(20);
		TranslateBar(g);

	}

	public void TranslateBar(GraphicsContext g) {
		y = yPosition- manager.getGameCamera().getyOffset();

		help2(0, xsline, y, xeline, y);
		help2(1,xsline2, y, xeline2, y);
		help2(2, xsline3, y, xeline3, y);
		help2(3, xsline4, y, xeline4, y);

		help2(4, Sxsline, y, Sxeline, y);
		help2(5, Sxsline2, y, Sxeline2, y);
		help2(6, Sxsline3, y, Sxeline3, y);
		help2(7, Sxsline4, y, Sxeline4, y);

		// 1er bar
		g.setStroke(Unit.colors[0]);
		help(lines.get(0),g);
		g.setStroke(Unit.colors[1]);
		help(lines.get(1),g);
		g.setStroke(Unit.colors[2]);
		help(lines.get(2),g);
		g.setStroke(Unit.colors[3]);
		help(lines.get(3),g);

		// 2eme bar
		g.setStroke(Unit.colors[0]);
		help(lines.get(4),g);
		g.setStroke(Unit.colors[1]);
		help(lines.get(5),g);
		g.setStroke(Unit.colors[2]);
		help(lines.get(6),g);
		g.setStroke(Unit.colors[3]);
		help(lines.get(7),g);

		clock();

	}

	private void help2(int a, int x1, float  y1, int x2, float y2){
		lines.get(a).setStartX(x1);
		lines.get(a).setStartY(y1);
		lines.get(a).setEndX(x2);
		lines.get(a).setEndY(y2);
	}

	private void help(Line l, GraphicsContext g)
	{
		g.strokeLine(l.getStartX(),l.getStartY(),l.getEndX(),l.getEndY());
	}

	@Override
	public boolean collidesWith(Ellipse body, int color) {
		for (int i = 0; i < lines.size(); i++) {
			if(body.getBoundsInLocal().intersects(lines.get(i).getBoundsInLocal())){
				System.out.println("collision detected in rectangle");
				if (color != getcolor(i)) return true;
			}
		}
		return false;
	}

	public int getcolor(int i) { 

		switch (i) {
		case 4:
			return 0;
		case 5:
			return 1;
		case 6:
			return 2;
		case 7:
			return 3;
		default:
			return i;
		}
	}
}