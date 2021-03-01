package sample;

import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;
import java.io.Serializable;

public interface ClickListener extends Serializable {
    public static final long serialVersionUID =30;
    public void onClick() throws IOException, ClassNotFoundException;
}