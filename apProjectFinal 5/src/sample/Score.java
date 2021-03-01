package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.io.*;

public class Score extends Model implements Serializable {

	private static final long serialVersionUID = 6;
	private static int playerScore;
	private Manager manager;

	public Score(Manager manager, float yPosition) {
		super(manager, yPosition);
		x = 20;
	}

	@Override
	public void clock() {
	}

	@Override
	public void render(GraphicsContext g) {
		g.setFill(Color.rgb(255, 0, 128));
		g.setFont(Font.font("Marker Felt",40));
		g.setTextAlign(TextAlignment.CENTER);
		g.fillText(String.valueOf(playerScore), x, yPosition);
	}
	public void reinit(Unit u){
	}


	@Override
	public boolean collidesWith(Ellipse body, int color) {
		return false;
	}

	public static int getPlayerScore(){return playerScore;}

	public static void setPlayerScore(int playerScore) throws IOException {
		Score.playerScore = playerScore;
		if(playerScore > readHighScore()) {
			storeHighScore(Score.playerScore);
		}
	}

	public static long readHighScore() {
		if (!new File("HighScore.txt").canRead()) return 0;
		try (DataInputStream dis = new DataInputStream(new FileInputStream("HighScore.txt"))) {
			return dis.readLong();
		} catch (IOException ignored) {
			return 0;
		}
	}

	public static void storeHighScore(long number) throws IOException {
		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("HighScore.txt",false))) {
			dos.writeLong(number);
		}
	}

}
