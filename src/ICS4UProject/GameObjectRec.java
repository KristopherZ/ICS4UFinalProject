package ICS4UProject;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class GameObjectRec extends GameObject {

    private Rectangle rec;
    private boolean updateX = true, updateY = true;
    private double fixedX,fixedY;

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
        if (updateX) {
            rec.setX(getRelativePosition().getX());
        }else{
            rec.setX(fixedX);
        }
        if (updateY) {
            rec.setY(getRelativePosition().getY());
        }else {
            rec.setY(fixedY);
        }
    }

    public Rectangle getRectangle(){
        return rec;
    }

    public void setUpdateX(boolean updateX, double x){
        this.updateX = updateX;
        this.fixedX  = x;
    }

    public void setUpdateY(boolean updateY,double y){
        this.updateY = updateY;
        this.fixedY = y;
    }

}