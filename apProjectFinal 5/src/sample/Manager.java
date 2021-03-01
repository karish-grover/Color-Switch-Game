package sample;

import javafx.scene.input.Mnemonic;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class Manager implements Serializable{

    private final ColorSwitch game;
    private static final long serialVersionUID = 13;


    public Manager(ColorSwitch game) {
        this.game = game;
    }

    public int getWidth() {
        return game.getWidth();
    }

    public void reinit(Manager manager){
        manager.game.reinit(manager.game);
    }

    public int getHeight() {
        return game.getHeight();
    }

    public MouseControl getMouseManager() {
        return game.getMouseManager();
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public ColorSwitch getGame() {
        return game;
    }
}
