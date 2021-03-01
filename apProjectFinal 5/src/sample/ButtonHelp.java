package sample;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.Serializable;

public abstract class ButtonHelp implements Serializable {
	private static final long serialVersionUID = 22;
	protected float x, y;

	transient protected Rectangle bounds;

	protected boolean hovering = false;
	protected int width, height;

	public ButtonHelp(Manager manager, float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle((int) x, (int) y, 2*width, 2*height);
	}

	public void reinit(ButtonHelp buttonHelp){
		buttonHelp.bounds = new Rectangle((int) x, (int) y, 2*width, 2*height);
	}

	public abstract void clock();
	public abstract void render(GraphicsContext g);
	public abstract void onClick() throws IOException, ClassNotFoundException;

	public void onMouseMove(MouseEvent e) {
		hovering = bounds.contains(e.getX(), e.getY());
	}

	public void onMouseRelease(MouseEvent e) throws IOException, ClassNotFoundException {
		System.out.println(hovering);
		if(hovering) onClick();
	}

	// GETTERS & SETTERS
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}

}
