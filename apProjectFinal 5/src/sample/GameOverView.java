package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.print.attribute.standard.MediaName;
import java.io.*;


public class GameOverView extends View implements Serializable {

	private static final long serialVersionUID = 18;
	private final UnitManager e;
	private final UIManager ui;
	private final Manager manager;
	private int i = 500;

	public void reinit(GameOverView v){
		v.e.reinit(v.e);
		v.ui.reinit(v.ui);
		v.manager.reinit(v.manager);

		for (int j = 0; j < v.ui.getObjects().size(); j++) {
			ImageButton b = (ImageButton) v.ui.getObjects().get(i);
			b.reinit(b, b.getImages());
		}

	}

	public GameOverView(Manager manager) {
		super(manager);
		e = new UnitManager(manager);
		ui = new UIManager(manager);
		this.manager=manager;
		manager.getMouseManager().setUIManager(ui);

		ui.addObject(new ImageButton(manager,10, getMidHeight()-80, 70, 70, Images.getRestart(), new ClickListener() {
			@Override
			public void onClick() throws IOException {
				Score.setPlayerScore(0);

				manager.getMouseManager().setUIManager(null);
				View gameView = new GameView(manager);
				View.setView(gameView);
				manager.getGame().setView(gameView);
//                closemusic();
			}
		}));
		ui.addObject(new ImageButton(manager,175, getMidHeight()-80, 70, 70, Images.getExit(), new ClickListener() {
			@Override
			public void onClick() throws IOException {
				Score.setPlayerScore(0);

				manager.getMouseManager().setUIManager(null);
				View menuView = new MenuView(manager);
				View.setView(menuView);
				manager.getGame().setView(menuView);
//                closemusic();
			}
		}));
		ui.addObject(new ImageButton(manager,345, getMidHeight()-80, 70, 70, Images.getStars(), new ClickListener() {
			@Override
			public void onClick() throws IOException {
				if(Score.getPlayerScore()>10){
					manager.getMouseManager().setUIManager(null);

					View gameView = new GameView(manager);
					View.setView(gameView);
					manager.getGame().setView(gameView);

				}
				else{

					Score.setPlayerScore(0);
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("");
					alert.setHeaderText("Stars count not sufficient");
					String s = "Number of stars should be more than 10";
					alert.setContentText(s);
					alert.show();

					manager.getMouseManager().setUIManager(null);
					View menuView = new MenuView(manager);
					View.setView(menuView);
					manager.getGame().setView(menuView);
				}
//                closemusic();
			}
		}));
	}


	
	@Override
	public void clock() {
		i++;
		e.clock();
		ui.clock();
	}

	@Override
	public void render(GraphicsContext g) {
		e.render(g);
		ui.render(g);
		g.drawImage(Images.getHighscore(), 100, 500, 300, 80);
		g.drawImage(Images.getGameover(), (int) (getMidWidth() - 150), 80, 300, 150);
		g.setFill(Color.WHITE);
		g.setFont(Font.font("Arial", FontWeight.BOLD, 50));
		g.fillText(String.valueOf(readHighScore()), 250, 630);
	}

	public static long readHighScore() {
		if (!new File("HighScore.txt").canRead()) return 0;
		try (DataInputStream dis = new DataInputStream(new FileInputStream("HighScore.txt"))) {
			return dis.readLong();
		} catch (IOException ignored) {
			return 0;
		}
	}
}
