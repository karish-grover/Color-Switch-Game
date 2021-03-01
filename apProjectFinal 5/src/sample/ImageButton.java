package sample;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.Serializable;

public class ImageButton extends ButtonHelp implements Serializable {
	private static final long serialVersionUID = 16;
	private transient Image[] images;
	private ClickListener clicker;

	public void reinit(ImageButton b, Image[] images){
		b.images = images;
		super.reinit(this);
	}


	public ImageButton(Manager handler, float x, float y, int width, int height, Image[] images, ClickListener click) {
		super(handler, x, y, width, height);
		this.images = images;
		super.x = x;
		this.clicker= click;
	}

	@Override
	public void clock() {
	}

	@Override
	public void render(GraphicsContext g) {
		//System.out.println(images);
			if(hovering) g.drawImage(images[1], (int) super.x+4, (int) y+3);
			else g.drawImage(images[0], (int) super.x+4, (int) y+3);
	}

	@Override
	public void onClick() throws IOException, ClassNotFoundException {
		clicker.onClick();
	}

	public Image[] getImages() {
		return images;
	}
}