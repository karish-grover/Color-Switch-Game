package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class UIManager implements Serializable {
	private static final long serialVersionUID = 4;
	private ArrayList<ButtonHelp> objects;
	private Manager manager;
	
	public UIManager(Manager manager) {
		objects = new ArrayList<ButtonHelp>();
		this.manager= manager;
	}
	
	public void clock() {
		for(ButtonHelp o : objects)
			o.clock();
	}

	public void render(GraphicsContext g) {
		for(ButtonHelp o : objects)
			o.render(g);
	}
	public void reinit(UIManager ui){
		ui.manager.reinit(ui.manager);
	}

	
	public void addObject(ButtonHelp o) {
		objects.add(o);
	}

	public ArrayList<ButtonHelp> getObjects(){
		return objects;
	}

	public void onMouseMove(MouseEvent e) {
		for(ButtonHelp o : objects)
			o.onMouseMove(e);
	}

	public void onMouseRelease(MouseEvent e) throws IOException, ClassNotFoundException {
		for(ButtonHelp o : objects)
			o.onMouseRelease(e);
	}
}
