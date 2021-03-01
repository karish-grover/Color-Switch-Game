package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Shape;

import java.io.Serializable;

public class obsrectangle extends Obstacle implements Serializable {
    private static final long serialVersionUID = 27;
    private static final int MAX_STATE = 400;
    private final int strokeval = 20;
    private final int xsizeFrame = 500;
    private final int xMidFrame = xsizeFrame/2;
    private final float yMidFrame = yPosition;

    transient Line linleft = new Line(xMidFrame-50-strokeval,yMidFrame-50-manager.getGameCamera().getyOffset(),xMidFrame-50-strokeval,yMidFrame+50-manager.getGameCamera().getyOffset());
    transient Line lintop = new Line(xMidFrame-50,yMidFrame-50-strokeval-manager.getGameCamera().getyOffset(),xMidFrame+50,yMidFrame-50-strokeval-manager.getGameCamera().getyOffset());
    transient Line linright = new Line(xMidFrame+50+strokeval,yMidFrame-50-manager.getGameCamera().getyOffset(),xMidFrame+50+strokeval,yMidFrame+50-manager.getGameCamera().getyOffset());
    transient Line linbot = new Line(xMidFrame-50,yMidFrame+50+strokeval-manager.getGameCamera().getyOffset(),xMidFrame+50,yMidFrame+50+strokeval-manager.getGameCamera().getyOffset());

    private int state;
    private int rangestate;

    public obsrectangle(Manager manager, float yPosition) {
        super(manager, yPosition);
        yPosition = yPosition - manager.getGameCamera().getyOffset();
    }

    public void reinit(Unit u){
        obsrectangle o = (obsrectangle)u;
        System.out.println("obsrectangle init called");
        o.linleft = new Line(xMidFrame-50-strokeval,yMidFrame-50-manager.getGameCamera().getyOffset(),xMidFrame-50-strokeval,yMidFrame+50-manager.getGameCamera().getyOffset());
        o.lintop = new Line(xMidFrame-50,yMidFrame-50-strokeval-manager.getGameCamera().getyOffset(),xMidFrame+50,yMidFrame-50-strokeval-manager.getGameCamera().getyOffset());
        o.linright = new Line(xMidFrame+50+strokeval,yMidFrame-50-manager.getGameCamera().getyOffset(),xMidFrame+50+strokeval,yMidFrame+50-manager.getGameCamera().getyOffset());
        o.linbot = new Line(xMidFrame-50,yMidFrame+50+strokeval-manager.getGameCamera().getyOffset(),xMidFrame+50,yMidFrame+50+strokeval-manager.getGameCamera().getyOffset());
    }

    @Override
    public void clock() {
        state+=2;
        if (state == MAX_STATE) {
            state = 0;
        }
    }

    @Override
    public void render(GraphicsContext g) {
        rotaterectangle(g);
    }


    @Override
    public boolean collidesWith(Ellipse body, int bodycolor) {
        boolean collision = false;

        if(((Path) Shape.intersect(body, linbot)).getElements().size() > 0){
            if((rangestate==0) && (bodycolor!=3)) {
                collision = true;
            }else if ((rangestate==1) && (bodycolor!=2)) {
                collision = true;
            }else if ((rangestate==2) && (bodycolor!=1)) {
                collision = true;
            }else if ((rangestate==3) && (bodycolor!=0)) {
                collision = true;
            }else collision = false;

        }else if(((Path) Shape.intersect(body, lintop)).getElements().size() > 0){
            if((rangestate==0) && (bodycolor!=1)){
                collision = true;
            }else if((rangestate==1) && (bodycolor!=0)) {
                collision = true;
            }else if((rangestate==2) && (bodycolor!=3)) {
                collision = true;
            }else if((rangestate==3) && (bodycolor!=2)) {
                collision = true;
            }else if((rangestate==0) && (bodycolor!=1)) {
                collision = true;
            }else collision = false;

        }else collision = false;

        return collision;
    }

    public void rotaterectangle(GraphicsContext g) {
        g.setLineWidth(strokeval);
        System.out.println(linleft);

        linleft.setStartX(xMidFrame-50-strokeval);
        linleft.setStartY(yMidFrame-50-manager.getGameCamera().getyOffset());
        linleft.setEndX(xMidFrame-50-strokeval);
        linleft.setEndY(yMidFrame+50-manager.getGameCamera().getyOffset());

        lintop.setStartX(xMidFrame-50);
        lintop.setStartY(yMidFrame-50-strokeval-manager.getGameCamera().getyOffset());
        lintop.setEndX(xMidFrame+50);
        lintop.setEndY(yMidFrame-50-strokeval-manager.getGameCamera().getyOffset());

        linright.setStartX(xMidFrame+50+strokeval);
        linright.setStartY(yMidFrame-50-manager.getGameCamera().getyOffset());
        linright.setEndX(xMidFrame+50+strokeval);
        linright.setEndY(yMidFrame+50-manager.getGameCamera().getyOffset());

        linbot.setStartX(xMidFrame-50);
        linbot.setStartY(yMidFrame+50+strokeval-manager.getGameCamera().getyOffset());
        linbot.setEndX(xMidFrame+50);
        linbot.setEndY(yMidFrame+50+strokeval-manager.getGameCamera().getyOffset());

        if (state < 100) {
            g.setStroke(colors[0]);
            g.strokeLine(linleft.getStartX(), linleft.getStartY(), linleft.getEndX(), linleft.getEndY());

            g.setStroke(colors[1]);
            g.strokeLine(lintop.getStartX(), lintop.getStartY(), lintop.getEndX(), lintop.getEndY());

            g.setStroke(colors[2]);
            g.strokeLine(linright.getStartX(), linright.getStartY(), linright.getEndX(), linright.getEndY());

            g.setStroke(colors[3]);
            g.strokeLine(linbot.getStartX(), linbot.getStartY(), linbot.getEndX(), linbot.getEndY());
            rangestate = 0;

        }else if(state <200) {
            g.setStroke(colors[3]);
            g.strokeLine(linleft.getStartX(), linleft.getStartY(), linleft.getEndX(), linleft.getEndY());

            g.setStroke(colors[0]);
            g.strokeLine(lintop.getStartX(), lintop.getStartY(), lintop.getEndX(), lintop.getEndY());

            g.setStroke(colors[1]);
            g.strokeLine(linright.getStartX(), linright.getStartY(), linright.getEndX(), linright.getEndY());

            g.setStroke(colors[2]);
            g.strokeLine(linbot.getStartX(), linbot.getStartY(), linbot.getEndX(), linbot.getEndY());
            rangestate = 1;

        }else if(state <300) {
            g.setStroke(colors[2]);
            g.strokeLine(linleft.getStartX(), linleft.getStartY(), linleft.getEndX(), linleft.getEndY());

            g.setStroke(colors[3]);
            g.strokeLine(lintop.getStartX(), lintop.getStartY(), lintop.getEndX(), lintop.getEndY());

            g.setStroke(colors[0]);
            g.strokeLine(linright.getStartX(), linright.getStartY(), linright.getEndX(), linright.getEndY());

            g.setStroke(colors[1]);
            g.strokeLine(linbot.getStartX(), linbot.getStartY(), linbot.getEndX(), linbot.getEndY());
            rangestate = 2;

        }else if (state <400) {
            g.setStroke(colors[1]);
            g.strokeLine(linleft.getStartX(), linleft.getStartY(), linleft.getEndX(), linleft.getEndY());

            g.setStroke(colors[2]);
            g.strokeLine(lintop.getStartX(), lintop.getStartY(), lintop.getEndX(), lintop.getEndY());

            g.setStroke(colors[3]);
            g.strokeLine(linright.getStartX(), linright.getStartY(), linright.getEndX(), linright.getEndY());

            g.setStroke(colors[0]);
            g.strokeLine(linbot.getStartX(), linbot.getStartY(), linbot.getEndX(), linbot.getEndY());

            rangestate = 3;
        }
    }
}
