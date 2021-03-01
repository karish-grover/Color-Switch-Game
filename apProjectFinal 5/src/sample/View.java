package sample;
import javafx.scene.canvas.GraphicsContext;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.Serializable;

abstract class View implements Serializable {

    private static final long serialVersionUID = 1;
    protected Manager manager;
    private final float midWidth;
    private final float midHeight;
    private static View currentView;
    private static Clip clip;

    public View(Manager manager) {
        this.manager = manager;
        this.midWidth = manager.getWidth() / 2;
        this.midHeight = manager.getHeight() / 2;
    }

    public void reinit(View view){
        view.manager.reinit(view.manager);
    }


    public abstract void clock();
    public abstract void render(GraphicsContext g);

    protected float getMidWidth(){
        return midWidth; }

    protected float getMidHeight(){
        return midHeight; }


    public static View getView() {
        return currentView;
    }

    public static void setView(View view) {
        currentView = view;
    }

    public static void getmusic() {
        // play the music
        //playSound("music.wav");
        Ball.playSound("African_Safari_Loop.wav");
    }

    public void closemusic() {
        clip.close();
    }
}