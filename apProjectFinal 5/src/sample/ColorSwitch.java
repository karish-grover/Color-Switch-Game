package sample;
import com.sun.prism.Graphics;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ColorSwitch extends Application implements Runnable, Serializable {

    private static final long serialVersionUID = 20;
    transient private Thread concurrent;

    private String title;
    private int width;
    private int height;
    private View view;
    private int highScore;
    private MouseControl mouseControl;
    private GameCamera gameCamera;

    static private int timeElapsed=0;

    transient private Scene scene;
    transient private Canvas canvas;
    transient private Group root;

    public void reinit(ColorSwitch colorSwitch){
        colorSwitch.root= new Group();
        colorSwitch.mouseControl = new MouseControl();

        colorSwitch.canvas = new Canvas(height, width);
        colorSwitch.canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MouseControl.mouseClicked(mouseEvent);
            }
        });

        colorSwitch.canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    MouseControl.mouseReleased(mouseEvent);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        colorSwitch.canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MouseControl.mousePressed(mouseEvent);
            }
        });


        colorSwitch.canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MouseControl.mouseMoved(mouseEvent);
            }
        });

        colorSwitch.canvas.setWidth(width);
        colorSwitch.canvas.setHeight(height);

        colorSwitch.root.getChildren().add(canvas);
        //startGame();
        colorSwitch.scene = new Scene(root,height, width);
        colorSwitch.scene.setFill(Color.BLACK);
    }

    private boolean running=false;

    @Override
    public void start(Stage primaryStage) throws Exception{
        width = 500;
        height = 700;
        title = "Color Switch";

        primaryStage.setTitle(title);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);
        primaryStage.setResizable(false);

        root = new Group();
        mouseControl = new MouseControl();

        canvas = new Canvas(height, width);
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MouseControl.mouseClicked(mouseEvent);
            }
        });

        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    MouseControl.mouseReleased(mouseEvent);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MouseControl.mousePressed(mouseEvent);
            }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MouseControl.mouseMoved(mouseEvent);
            }
        });

        canvas.setWidth(width);
        canvas.setHeight(height);

        root.getChildren().add(canvas);
        startGame();
        scene = new Scene(root,height, width);
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setOpacity(0.95);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void initializeScreen() throws IOException, ClassNotFoundException {
        Images.initializeImages();
        Manager manager = new Manager(this);
        gameCamera = new GameCamera(manager, 0);

        view = new MenuView(manager);
        View.setView(view);
    }

    private void clock() {
        if(View.getView() != null) {
            View.getView().clock();
        } else {
            View.setView(view);
            View.getView().clock();;
        }
    }

    private void render() {
        GraphicsContext g = canvas.getGraphicsContext2D();

        g.setFill(Color.BLACK);
        g.fillRect(0, 0, width, height);

        if(View.getView() != null) {
            View.getView().render(g);
        }
        View.setView(view);
    }

    @Override
    public void run() {
        try {
            initializeScreen();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Timeline t = new Timeline(new KeyFrame(Duration.millis(17), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                timeElapsed++;
                clock();
                render();
            }
        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
        stopGame();
    }

    protected synchronized void startGame() {
        if(running)
            return;
        running = true;
        concurrent = new Thread(this);
        concurrent.start();
    }

    private synchronized void stopGame() {
        if(!running)
            return;
        running = false;
        try {
            concurrent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public GameCamera getGameCamera() { return gameCamera;}

    public int getRandom(int range) {
        Random ran = new Random();
        return ran.nextInt(range);
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Group getRoot() {
        return this.root;
    }

    public MouseControl getMouseManager() {
        return mouseControl;
    }

    public void setView(View v){
        this.view = v;
    }

    public View getView(){ return view;}

    public static int getTimeElapsed(){
        return timeElapsed;
    }

}
