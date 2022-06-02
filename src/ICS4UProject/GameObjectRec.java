package ICS4UProject;

import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.effect.MotionBlur;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class GameObjectRec extends GameObject {

    private MotionBlur motionBlur = new MotionBlur();
    private Rectangle rec;

    public GameObjectRec(double x, double y, double sizeX, double sizeY) {
        super(x,y);
        rec = new Rectangle(x,y,sizeX,sizeY);
    }

    public GameObjectRec(Vector v, double sizeX, double sizeY){
        this(v.getX(),v.getY(),sizeX,sizeY);
    }

    @Override
    public boolean isCollide(Object o){
        return rec.getBoundsInParent().intersects(((Node)o).getBoundsInParent());
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        motionBlur.setAngle(getVelocity().getAngle());
        motionBlur.setRadius(getVelocity().length()*0.01);
        rec.setEffect(motionBlur);
        rec.setX(getPosition().getX());
        rec.setY(getPosition().getY());
    }

    public Rectangle getRectangle(){
        return rec;
    }

}