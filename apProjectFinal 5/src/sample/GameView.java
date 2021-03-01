package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;


public class GameView extends View implements Serializable {

	private static final long serialVersionUID = 17;
	private final Manager manager;
	private UnitManager em;
	private Ball player;
	private ColorChangeBall switcher;
	private Star scoreStar;
	private final Score score;
	private UIManager ui;
	private String username=null;
	private static int obstacleSpeed=1;
	private int playerscore;
	private static View lastView;

	public void reinit(GameView g){

		g.manager.reinit(g.manager);

		makeArc.setIsMenu(false);
		g.em.reinit(g.em);
		g.player.reinit(g.player);
		g.switcher.reinit(g.switcher);
		g.scoreStar.reinit(g.scoreStar);
		g.score.reinit(g.score);
		g.ui.reinit(g.ui);

		g.manager.getMouseManager().setUIManager(g.ui);

		for (int i = 0; i < g.em.getUnits().size(); i++) {
			g.em.getUnits().get(i).reinit(g.em.getUnits().get(i));
		}

		ImageButton b = (ImageButton) g.ui.getObjects().get(0);
		b.reinit(b, Images.getPauseButton());

	}

	public GameView(Manager manager) throws IOException {
		super(manager);
		makeArc.setIsMenu(false);
		this.manager = manager;
		float midHeight = manager.getHeight() / 2;
		playerscore= 0;
		ui = new UIManager(manager);
		score = new Score(manager, 50);
		manager.getMouseManager().setUIManager(ui);
		em = new UnitManager(manager);

		scoreStar = new Star(manager, midHeight - 200, 10, 20);
		em.addUnit(scoreStar);
		
		switcher = new ColorChangeBall(manager, 400);
		em.addUnit(switcher);

		player = new Ball(manager, manager.getHeight() - 20, 0);
		em.addUnit(player);

		ui.addObject(new ImageButton(manager, 435,5, 70, 70, Images.getPauseButton(), new ClickListener() {
			@Override
			public void onClick() {
				//View.setView(manager.getGame().getView());
				lastView = manager.getGame().getView();
				manager.getMouseManager().setUIManager(null);
				View pauseView = new PauseView(manager);
			    View.setView(pauseView);
				manager.getGame().setView(pauseView);
			}
		}));
	}

	@Override
	public void clock() {
		ui.clock();
		em.clock();
		try {
			if (isGameOver()) {
				manager.getGame().getMouseManager().setUIManager(null);
				View gameOverView = new GameOverView(manager);
				View.setView(gameOverView);
				manager.getGame().setView(gameOverView);
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		if(player.getyPosition() < em.getUnits().get(em.getUnits().size()-1).getyPosition()+200)
			randomSpawn();
	}

	@Override
	public void render(GraphicsContext g) {
		ui.render(g);
		em.render(g);
		score.render(g);
	}

	private boolean checkCollisions() throws IOException {
		System.out.println(em.getUnits());
		for(int i = 0; i < em.getUnits().size() ; i++) {

			Unit currentUnit = em.getUnits().get(i);

			if(currentUnit == player)
				continue;

			if(currentUnit.collidesWith(player.getP(), player.getColorType())) {

				if(currentUnit.getClass() == switcher.getClass()) {
					//Player.playSound("switch.wav");
					em.getUnits().remove(currentUnit);
					player.setColor(manager.getGame().getRandom(4));
					return false;
				}

				if(currentUnit == scoreStar) {
					//Player.playSound("score.wav");
					scoreStar.setyPosition(scoreStar.getyPosition() - 300);
					playerscore++;
					Score.setPlayerScore(playerscore);
					return false;
				}
				//	Player.playSound("explosion.wav");
				return true;
			}
		}
		return false;
	}



	private void randomSpawn() {
			int y = manager.getGame().getRandom(4) ;
			int spawnHeight = (int) (em.getUnits().get(em.getUnits().size() - 1).getyPosition() - 300);
			switch (y) {
				case 0 -> em.addUnit(new Circle(manager, spawnHeight, 200, obstacleSpeed*(1+ColorSwitch.getTimeElapsed()/10000), 0));
				case 1 -> em.addUnit(new ColorChangeBall(manager, spawnHeight + 200));
				case 2 -> em.addUnit(new obsrectangle(manager, spawnHeight));
				case 3 -> em.addUnit(new Rectangle(manager, spawnHeight, obstacleSpeed*(1+ColorSwitch.getTimeElapsed()/10000)));
			}
	}

	public void setUsername(String username){
		this.username= username;
	}

	public String getUsername(){
		return username;
	}

	private boolean isGameOver() throws IOException {
		return checkCollisions();
	}

	public Manager getManager(){
		return manager;
	}

	public UnitManager getEm() {
		return em;
	}

	public Ball getPlayer() {
		return player;
	}

	public ColorChangeBall getSwitcher() {
		return switcher;
	}

	public Star getScoreStar() {
		return scoreStar;
	}

	public Score getScore() {
		return score;
	}

	public UIManager getUi() {
		return ui;
	}
	public int getPlayerscore() {
		return playerscore;
	}

	public static int getObstacleSpeed() {
		return obstacleSpeed;
	}

	public static void setObstacleSpeed(int sp){
		obstacleSpeed = sp;
	}

	public static View getLastView(){return lastView;}
}