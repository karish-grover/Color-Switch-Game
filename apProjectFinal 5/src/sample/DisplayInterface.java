package sample;

import javafx.scene.canvas.GraphicsContext;

import java.io.Serializable;

public interface DisplayInterface extends Serializable {
    public void clock();
    public void render(GraphicsContext g);
}
