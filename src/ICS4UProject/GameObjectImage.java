package ICS4UProject;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameObjectImage extends GameObject {

    ImageView image;
    private boolean updateX = true, updateY = true;
    private double fixedX,fixedY;

    public GameObjectImage(double x, double y, double sizeX, double sizeY, Image image) {
        super(x,y);
        this.image = new ImageView(image);
        this.image.setFitWidth(sizeX);
        this.image.setFitHeight(sizeY);
    }

    public GameObjectImage(Vector v, double sizeX, double sizeY, Image image){
        this(v.getX(),v.getY(),sizeX,sizeY, image);
    }

    @Override
    public boolean isCollide(Object o){
        return image.getBoundsInParent().intersects(((Node)o).getBoundsInParent());
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        if(updateX){
            image.setX(getRelativePosition().getX());
        }else{
            image.setX(fixedX);
        }
        if (updateY) {
            image.setY(getRelativePosition().getY());
        }else {
            image.setY(fixedY);
        }
    }

    public ImageView getImage(){
        return image;
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