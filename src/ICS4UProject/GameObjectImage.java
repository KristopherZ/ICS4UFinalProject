package ICS4UProject;

import javafx.scene.Node;
import javafx.scene.effect.MotionBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameObjectImage extends GameObject {

    private MotionBlur motionBlur = new MotionBlur();
    ImageView image;


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
        motionBlur.setAngle(getVelocity().getAngle());
        motionBlur.setRadius(getVelocity().length()*0.01);
        image.setEffect(motionBlur);
        image.setX(getPosition().getX());
        image.setY(getPosition().getY());
    }

    public ImageView getImage(){
        return image;
    }

}