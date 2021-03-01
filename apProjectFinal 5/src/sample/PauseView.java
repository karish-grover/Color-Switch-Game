package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

import java.io.*;
import java.util.ArrayList;

public class PauseView extends View {
    private UnitManager e;
    private UIManager ui;
    private static final long serialVersionUID = 8;

    private static int serializeCounter=0;

    public PauseView(Manager manager){
        super(manager);
        makeArc.setIsMenu(false);
        ui = new UIManager(manager);
        e = new UnitManager(manager);
        manager.getMouseManager().setUIManager(ui);

        ui.addObject(new ImageButton(manager,20, getMidHeight()-80, 70, 70, Images.getResume(), new ClickListener() {
            @Override
            public void onClick() throws IOException {
               View gameView = new GameView(manager);
                manager.getGame().setView(GameView.getLastView());
//                closemusic();
            }
        }));

        ui.addObject(new ImageButton(manager,175, getMidHeight()-80, 70, 70, Images.getExit(), new ClickListener() {
            @Override
            public void onClick() {
              //  System.exit(1);

                manager.getMouseManager().setUIManager(null);
                View menuView = new MenuView(manager);
                View.setView(menuView);
                manager.getGame().setView(menuView);

//                closemusic();
            }
        }));

        ui.addObject(new ImageButton(manager,340, getMidHeight()-80, 70, 70, Images.getSave(), new ClickListener() {
            @Override
            public void onClick() throws IOException {
                TextInputDialog td = new TextInputDialog("Name");
                td.setHeaderText("Enter your username");
                td.getEditor().setFont(Font.font("Marker Felt"));
                td.showAndWait();
                String username = td.getEditor().getText();
                GameView g = (GameView) GameView.getLastView();

                g.setUsername(username);
                serialize((GameView)GameView.getLastView());

                manager.getMouseManager().setUIManager(null);
                View menuView = new MenuView(manager);

                manager.getGame().setView(menuView);
//                closemusic();
            }
        }));
    }

    public void reinit(){
    }


    @Override
    public void clock() {

    }

    public static void serialize(GameView game) throws IOException {
        serializeCounter++;
        ObjectOutputStream out = null;
        try{
            boolean exists = new File("savedGames.txt").exists();
            FileOutputStream fos;

            if(serializeCounter<=5){ fos = new FileOutputStream("savedGames.txt", true);}
            else{ fos = new FileOutputStream("savedGames.txt", false);}

            out = exists ? new ObjectOutputStream(fos) {
                        protected void writeStreamHeader() throws IOException {
                            reset();
                        }
                    }:new ObjectOutputStream(fos);
            out.writeObject(game);
        }
        finally{
            assert out != null: "Output file is null!";
            out.close();
        }
    }

    @Override
    public void render(GraphicsContext g) {
        e.render(g);
        g.drawImage(Images.getTitle(), 122, 70);//Draw title on this view
        g.drawImage(Images.getPauseGame(), 65, 425);
        ui.render(g);
    }
}
