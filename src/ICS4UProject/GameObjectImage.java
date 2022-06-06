package ICS4UProject;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class GameObjectImage extends GameObject implements KineticsWithSize{

    private ImageView image;
    private double sizeX, sizeY;

    public GameObjectImage(double x, double y, double sizeX, double sizeY, Image image) {
        super(x,y);
        this.image = new ImageView(image);
        this.image.setFitWidth(sizeX);
        this.image.setFitHeight(sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
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
        updateRelativePosition();
        image.setX(getRelativePosition().getX());
        image.setY(getRelativePosition().getY());
        updatePosition(elapsedTime);
    }

    public ImageView getImage(){
        return image;
    }


    @Override
    public double getSizeX() {
        return sizeX;
    }

    @Override
    public double getSizeY() {
        return sizeX;
    }

    @Override
    public void setSizeX(double x) {
        sizeX = x;
    }

    @Override
    public void setSizeY(double y) {
        sizeY = y;
    }
}