package sample;
import javafx.scene.canvas.GraphicsContext;

import java.io.IOException;
import java.io.Serializable;
import java.time.Instant;


public class MenuView extends View implements Serializable {

    private static final long serialVersionUID = 12;

    private UnitManager e;
    private UIManager ui;
    private static Instant startTime;
    private static int circleHeight=350;

    public void reinit(){
    }

    public MenuView(Manager manager) {
        super(manager);
        e = new UnitManager(manager);
        ui = new UIManager(manager);
        manager.getMouseManager().setUIManager(ui);
        makeArc.setIsMenu(true);

        //makeArc.isMenu=true;
        e.addUnit(new Circle(manager, circleHeight, 200, 5, 0));
        e.addUnit(new Circle(manager, circleHeight, 150, 1, 0));
        e.addUnit(new Circle(manager, circleHeight, 100, 4, 0));

        ui.addObject(new ImageButton(manager, manager.getWidth() / 2 - 35,getMidHeight() - 35, 70, 70, Images.getBtn_start(), new ClickListener() {
            @Override
            public void onClick() throws IOException {
                startTime= Instant.now();
                manager.getMouseManager().setUIManager(null);
                View gameView = new GameView(manager);
                View.setView(gameView);
                manager.getGame().setView(gameView);
                closemusic();
            }
        }));

        ui.addObject(new ImageButton(manager, 10, getMidHeight() + 150, 70, 70, Images.getExit(), new ClickListener() {
            @Override
            public void onClick() {
                closemusic();
                System.exit(0);

            }
        }));

        ui.addObject(new ImageButton(manager, 175, getMidHeight() + 150, 70, 70, Images.getResume(), new ClickListener() {
            @Override
            public void onClick() throws IOException, ClassNotFoundException {
                manager.getMouseManager().setUIManager(null);
                View resumeView = new ResumeView(manager);
                View.setView(resumeView);
                manager.getGame().setView(resumeView);
                closemusic();
            }
        }));

        ui.addObject(new ImageButton(manager, 340, getMidHeight() + 150, 70, 70, Images.getChooseLevel(), new ClickListener() {
            @Override
            public void onClick() {
                manager.getMouseManager().setUIManager(null);
                View levelView = new LevelView(manager);
                View.setView(levelView);
                manager.getGame().setView(levelView);
        closemusic();
            }
        }));
    }


    int i = 500;

    @Override
    public void clock() {
        e.clock();
        ui.clock();
        if (i == 500) {
            getmusic();
            i = 0;
        }
        i++;
    }

    @Override
    public void render(GraphicsContext g) {
        e.render(g);
        g.drawImage(Images.getTitle(), 122, 70);
        ui.render(g);
    }
}
