package sample;

import java.io.Serializable;

public class GameCamera implements Serializable {
	private static final long serialVersionUID = 19;
	private final Manager manager;
	private float yOffset;
	
	public GameCamera(Manager manager, float yOffset) {
		this.manager = manager;
		this.yOffset = yOffset;
	}

	public void updatePlayerView(Unit e) {
		yOffset = e.getyPosition() - manager.getHeight() / 2;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void reinit(GameCamera gameCamera){
		gameCamera.manager.reinit(gameCamera.manager);
	}
}
