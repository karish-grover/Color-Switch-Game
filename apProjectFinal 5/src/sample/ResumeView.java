package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.*;
import java.util.ArrayList;

public class ResumeView extends View implements Serializable {

    private static final long serialVersionUID = 7;
    private UnitManager e;
    private UIManager ui;
    private Manager manager;
    private ArrayList<GameView> savedGames;

    public ResumeView(Manager manager) throws IOException, ClassNotFoundException {
        super(manager);
        ui = new UIManager(manager);
        e = new UnitManager(manager);
       this.manager= manager;
       manager.getMouseManager().setUIManager(ui);
       savedGames= deserialize();

        int start = 360;
        ArrayList<Label> labels = new ArrayList<>();

        for (int i = 0; i < savedGames.size(); i++) {
            Label label = new Label(savedGames.get(i).getUsername());
            label.setTranslateX(175);
            label.setTranslateY(start);
            label.setTextFill(Color.rgb(255, 0, 128));
            label.setFont(Font.font("Marker Felt",40));
            labels.add(label);

      label.setOnMouseEntered(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
              label.setTextFill(Color.rgb(255, 255, 255));
          }
      });

      label.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    label.setTextFill(Color.rgb(255, 0, 128));
                }
            });

            GameView g = savedGames.get(i);

            label.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for (Label value : labels) {
                        value.setText("");
                    }
                    g.reinit(g);
                    View.setView(g);
                    manager.getGame().setView(g);
                }
            });

            manager.getGame().getRoot().getChildren().add(label);
            start+=50;
        }
    }

    public void reinit(){
    }

    public static ArrayList<GameView> deserialize() throws IOException, ClassNotFoundException {
        ArrayList<GameView> savedGames = new ArrayList<>();

        FileInputStream fis = new FileInputStream("savedGames.txt");
        ObjectInputStream in = new ObjectInputStream(fis);

        while(fis.available() > 0) {
            try {
                GameView game = (GameView) in.readObject();
                savedGames.add(game);
            } catch (ClassNotFoundException | NotSerializableException e) {
                e.printStackTrace();
            }
        }
        in.close();
        return savedGames;
    }

    @Override
    public void clock() {
    }

    @Override
    public void render(GraphicsContext g) {
        e.render(g);
        g.drawImage(Images.getResumeGame(), 85, 70);
        g.drawImage(Images.getChooseGame(), 55, 250);
        ui.render(g);
    }
}
