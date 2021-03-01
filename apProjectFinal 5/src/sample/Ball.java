package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.Serializable;

public class Ball extends Model implements Serializable {
	private static final long serialVersionUID = 23;
	private static final int DEFAULT_DIAMETER = 20;
	private boolean start = false, isGameOver = false;
	private int i = 0, color;
	private int midLine;

	transient private Ellipse p;

	public void reinit(Unit u){
		Ball ball = (Ball)u;
		System.out.println("ball init called");
		ball.p = new Ellipse(x - DEFAULT_DIAMETER / 2, yPosition , DEFAULT_DIAMETER, DEFAULT_DIAMETER);
	}

	public Ball(Manager manager, float yPosition, int color) {
		super(manager, yPosition);
		this.color = color;
		midLine = manager.getHeight() / 2;
		p = new Ellipse(x - DEFAULT_DIAMETER / 2, yPosition , DEFAULT_DIAMETER, DEFAULT_DIAMETER);
	}

	@Override
	public void clock() {
		getInput();
		move();
		fall();
		updateLines();
		manager.getGameCamera().updatePlayerView(this);
	}

	@Override
	public void render(GraphicsContext g) {

		int xPos = (int) (x - DEFAULT_DIAMETER / 2);
		int yPos = (int) (yPosition - manager.getGameCamera().getyOffset());

		g.setFill(Unit.colors[color]);

		p.setCenterX(xPos);
		p.setCenterY(yPos);
		p.setRadiusX(DEFAULT_DIAMETER);
		p.setRadiusY(DEFAULT_DIAMETER);

		g.fillOval(p.getCenterX(),p.getCenterY(),p.getRadiusX(),p.getRadiusY());

		if(!start) {
			g.setFill(Color.rgb(255, 0, 128));
			g.setFont(Font.font("Marker Felt",40));
			g.setTextAlign(TextAlignment.CENTER);
			g.fillText("Click To Start!",manager.getWidth() / 2 , manager.getHeight() - 50);
		}

	}

	public void getInput() {
		xMove = 0;
		yMove = 0;
		if(MouseControl.getLeftPressed()) {
			yMove = -speed;
		}
	}

	@Override
	public void fall() {
		System.out.println(MouseControl.getLeftPressed());

		if(yPosition <= 680) {
			if(MouseControl.getLeftPressed()) {
				start = true;
				i++;
			}
			if(i>1) {
				if ((start) && (yMove == 0) && !MouseControl.getLeftPressed()){
					System.out.println("----");
					yPosition += speed * 0.15f;
					//	isMoving = false;
				}
			}
		}
	}

	public void updateLines() {
		if(p.getCenterY() <= midLine) {
			midLine += yMove;
		}
	}

	@Override
	public boolean collidesWith(Ellipse entity, int colors) {
		System.out.println("ball collides With");
		if(p.getBoundsInLocal().intersects(entity.getBoundsInLocal())){
			return color == colors;
		}
		return false;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}

	public int getColorType() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Ellipse getP() {
		return p;
	}


	public static synchronized void playSound(String url) {
		try {
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(Ball.class.getResourceAsStream("/sample/textures/" + url));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error : " + e.getMessage());
		}
	}

}
