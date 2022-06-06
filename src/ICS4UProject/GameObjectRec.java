package ICS4UProject;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class GameObjectRec extends GameObject implements KineticsWithSize{

    private Rectangle rec;
    private double sizeX, sizeY;

    public GameObjectRec(double x, double y, double sizeX, double sizeY) {
        super(x,y);
        rec = new Rectangle(x,y,sizeX,sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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
        updateRelativePosition();
        rec.setX(getRelativePosition().getX());
        rec.setY(getRelativePosition().getY());
        updatePosition(elapsedTime);

    }

    public Rectangle getRectangle(){
        return rec;
    }

    public double getSizeX() {
        return sizeX;
    }

    public double getSizeY(){
        return sizeY;
    }

    public void setSizeX(double x){
        rec.setWidth(x);
        sizeX = x;
    }

    public void setSizeY(double y){
        rec.setHeight(y);
        sizeY = y;
    }
}