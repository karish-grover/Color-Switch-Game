package sample;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.io.Serializable;


public class MouseControl implements Serializable {

    private static final long serialVersionUID = 10;
    private static boolean leftPressed, rightPressed;
    private static boolean isClicked;
    private static UIManager uiManager;


    public void clock() {

    }


    public void reinit(MouseControl m){
        MouseControl.uiManager.reinit(MouseControl.uiManager);
    }

    public static void mousePressed(MouseEvent e) {

        if(e.getEventType()==MouseEvent.MOUSE_PRESSED){
            leftPressed = true;}
        else if(e.isSecondaryButtonDown()) {
            rightPressed = true;
        }
    }

    public static boolean getLeftPressed(){
        return leftPressed;
    }

    public static boolean getRightPressed(){return rightPressed;}

    public static void mouseReleased(MouseEvent e) throws IOException, ClassNotFoundException {
        if(e.getEventType()==MouseEvent.MOUSE_RELEASED){
            System.out.println("*****");
            leftPressed = false;}
        else if(e.isSecondaryButtonDown()){
            rightPressed = false;}
        if(uiManager != null) {
            uiManager.onMouseRelease(e);
        }
    }

    public static void mouseClicked(MouseEvent e) {
        if(e.isPrimaryButtonDown())
            isClicked = true;
    }

    public static void mouseMoved(MouseEvent e) {
        if(uiManager != null)
            uiManager.onMouseMove(e);
    }

    public void setUIManager(UIManager uiManager) {
        MouseControl.uiManager = uiManager;
    }
}
