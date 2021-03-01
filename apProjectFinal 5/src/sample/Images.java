package sample;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class Images implements Serializable {
    private static final long serialVersionUID = 15;
    private transient static Image [] btn_start, exit, chooseLevel, resume, easy, medium, hard, save, restart, star, pauseButton;
    private static transient Image title, gameover, highscore, infinite, replay, title2, pauseGame, resumeGame, chooseGame;

    public void reinit(Images i){
        //NOTHING
    }

    public static void initializeImages() throws FileNotFoundException {

        btn_start = new Image[2];
        exit = new Image[2];
        resume = new Image[2];chooseLevel= new Image[2];
        easy = new Image[2];
        medium = new Image[2];
        hard= new Image[2];
        save = new Image[2];
        star= new Image[2];
        restart= new Image[2];
        pauseButton= new Image[2];

        star[0]= new Image(Images.class.getResourceAsStream("/sample/textures/stars.png"));
        star[1]= new Image(Images.class.getResourceAsStream("/sample/textures/starsH.png"));

        save[0]= new Image(Images.class.getResourceAsStream("/sample/textures/gameOver-min.png"));
        PixelReader p10 = save[0].getPixelReader();
        save[0]= new WritableImage(p10,10, 1, 135, 122);

        save[1]= new Image(Images.class.getResourceAsStream("/sample/textures/gameOverH.png"));
        PixelReader p106 = save[1].getPixelReader();
        save[1]= new WritableImage(p106,10, 1, 135, 122);

        restart[0]= new Image(Images.class.getResourceAsStream("/sample/textures/gameOver-min.png"));
        PixelReader p11 = restart[0].getPixelReader();
        restart[0]= new WritableImage(p11,155, 1, 130, 122);

        restart[1]= new Image(Images.class.getResourceAsStream("/sample/textures/gameOverH.png"));
        PixelReader p118 = restart[1].getPixelReader();
        restart[1]= new WritableImage(p118,155, 1, 130, 122);

        pauseGame= new Image(Images.class.getResourceAsStream("/sample/textures/paused-min.png"));
        pauseButton[0]= new Image(Images.class.getResourceAsStream("/sample/textures/pauseButton-min.png"));
        pauseButton[1]= new Image(Images.class.getResourceAsStream("/sample/textures/pauseButton-min.png"));
        resumeGame = new Image(Images.class.getResourceAsStream("/sample/textures/resumeGame-min.png"));
        chooseGame = new Image(Images.class.getResourceAsStream("/sample/textures/chooseGame-min.png"));

        title = new Image(Images.class.getResourceAsStream("/sample/textures/title.png"));
        title2 = new Image(Images.class.getResourceAsStream("/sample/textures/title2-min.png"));

        gameover = new Image(Images.class.getResourceAsStream("/sample/textures/gameover.png"));
        highscore = new Image(Images.class.getResourceAsStream("/sample/textures/highscore.png"));
        infinite = new Image(Images.class.getResourceAsStream("/sample/textures/infinite.png"));//Infinite symbol

        btn_start[0]= new Image(Images.class.getResourceAsStream("/sample/textures/ui.png"));
        PixelReader p = btn_start[0].getPixelReader();
        btn_start[0]= new WritableImage(p,0, 0, 64, 64);

        btn_start[1]= new Image(Images.class.getResourceAsStream("/sample/textures/ui.png"));
        PixelReader p100 = btn_start[1].getPixelReader();
        btn_start[1]= new WritableImage(p100,64, 0, 64, 64);

        exit[0]= new Image(Images.class.getResourceAsStream("/sample/textures/menuB.png"));
        PixelReader p2 = exit[0].getPixelReader();
        exit[0]= new WritableImage(p2,10, 1, 145, 128);

        resume[0]= new Image(Images.class.getResourceAsStream("/sample/textures/menuB.png"));
        PixelReader p3 = resume[0].getPixelReader();
        resume[0]= new WritableImage(p3,155, 1, 130, 128);

        chooseLevel[0]= new Image(Images.class.getResourceAsStream("/sample/textures/menuB.png"));
        PixelReader p4 = chooseLevel[0].getPixelReader();
        chooseLevel[0]= new WritableImage(p4,290, 1, 130, 128);

        exit[1]= new Image(Images.class.getResourceAsStream("/sample/textures/menuB3.png"));
        PixelReader p21 = exit[1].getPixelReader();
        exit[1]= new WritableImage(p21,10, 1, 145, 128);

        resume[1]= new Image(Images.class.getResourceAsStream("/sample/textures/menuB3.png"));
        PixelReader p31 = resume[1].getPixelReader();
        resume[1]= new WritableImage(p31,155, 1, 130, 128);

        chooseLevel[1]= new Image(Images.class.getResourceAsStream("/sample/textures/menuB3.png"));
        PixelReader p41 = chooseLevel[1].getPixelReader();
        chooseLevel[1]= new WritableImage(p41,290, 1, 130, 128);

        easy[0]= new Image(Images.class.getResourceAsStream("/sample/textures/easy-min.png"));
        medium[0]= new Image(Images.class.getResourceAsStream("/sample/textures/medium-min.png"));
        hard[0]= new Image(Images.class.getResourceAsStream("/sample/textures/hard-min.png"));

        easy[1]= new Image(Images.class.getResourceAsStream("/sample/textures/easyH.png"));
        medium[1]= new Image(Images.class.getResourceAsStream("/sample/textures/mediumH.png"));
        hard[1]= new Image(Images.class.getResourceAsStream("/sample/textures/hardH.png"));
    }



    public static Image getTitle(){return title;}
    public static Image getGameover(){return gameover;}
    public static Image getHighscore(){return highscore;}
    public static Image getTitle2(){return title2;}
    public static Image getPauseGame(){return pauseGame;}
    public static Image getChooseGame(){return chooseGame;}
    public static Image getResumeGame(){return resumeGame;}

    public static Image[] getPauseButton(){return pauseButton;}
    public static Image[] getBtn_start(){return btn_start;}
    public static Image[] getExit(){return exit;}
    public static Image[] getChooseLevel(){return chooseLevel;}
    public static Image[] getResume(){return resume;}
    public static Image[] getEasy(){return easy;}
    public static Image[] getMedium(){return medium;}
    public static Image[] getHard(){return hard;}
    public static Image[] getRestart(){return restart;}
    public static Image[] getSave(){return save;}
    public static Image[] getStars(){return star;}
}
