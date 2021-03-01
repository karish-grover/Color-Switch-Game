package sample;

import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;
import java.io.Serializable;

public class LevelView extends View implements Serializable {

    private static final long serialVersionUID = 14;
    private UnitManager e;
    private UIManager ui;

    public void reinit(LevelView l){
    }

    public LevelView(Manager manager) {
        super(manager);
        e = new UnitManager(manager);
        ui = new UIManager(manager);
        manager.getMouseManager().setUIManager(ui);

        ui.addObject(new ImageButton(manager,100, getMidHeight()-200, 70, 70, Images.getEasy(), new ClickListener() {
            @Override
            public void onClick() throws IOException {
                GameView.setObstacleSpeed(1);
                manager.getMouseManager().setUIManager(null);
                View gameView = new GameView(manager);
                View.setView(gameView);
                manager.getGame().setView(gameView);
//                closemusic();
            }
        }));
        ui.addObject(new ImageButton(manager, 100,getMidHeight()-30, 70, 70, Images.getMedium(), new ClickListener() {
            @Override
            public void onClick() throws IOException {
                GameView.setObstacleSpeed(2);
                manager.getMouseManager().setUIManager(null);
                View gameView = new GameView(manager);
                View.setView(gameView);
                manager.getGame().setView(gameView);
//                closemusic();
            }
        }));
        ui.addObject(new ImageButton(manager,100,getMidHeight()+140, 70, 70, Images.getHard(), new ClickListener() {
            @Override
            public void onClick() throws IOException {
                GameView.setObstacleSpeed(3);
                manager.getMouseManager().setUIManager(null);
                View gameView = new GameView(manager);
                View.setView(gameView);
                manager.getGame().setView(gameView);
//                closemusic();
            }
        }));
    }

    @Override
    public void clock() {
        e.clock();
        ui.clock();
    }

    @Override
    public void render(GraphicsContext g) {
        e.render(g);
        g.drawImage(Images.getTitle2(), 37, 40);
        ui.render(g);
    }
}
