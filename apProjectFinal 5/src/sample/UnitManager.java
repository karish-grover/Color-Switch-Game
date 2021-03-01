package sample;
import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;
import java.util.ArrayList;

public class UnitManager implements DisplayInterface, Serializable {

	private static final long serialVersionUID = 2;
	private Manager manager;
	private ArrayList<Unit> units;
	
	public UnitManager(Manager manager) {
		this.manager = manager;
		units = new ArrayList<Unit>();
	}

	@Override
	public void clock() {
		for (Unit e : units) {
			e.clock();
		}
	}

	@Override
	public void render(GraphicsContext g) {
		for(Unit e : units) {
			e.render(g);
		}
	}

	public void reinit(UnitManager u){
		u.manager.reinit(u.manager);
	}

	
	public void addUnit(Unit e) {
		units.add(e);
	}

	// Getters & Setters
	
	public ArrayList<Unit> getUnits() {
		return units;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
