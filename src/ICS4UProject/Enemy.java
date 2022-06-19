package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Enemy extends CollisionBodyImage {

    private final static double coefficientOfZerothTerm = 100, coefficientOfFirstTerm = 50, exponent= 1.7;

    private ArrayList<PlatformImage> platformImageList = new ArrayList<>();

    public Enemy(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
        setElasticity(new double[]{1,1,1,1});
    }

    public ArrayList<PlatformImage> getPlatformImageList() {
        return platformImageList;
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
//        for(Platform i : platformList) {
//            if(i.collideWith(this).getCollisionPosition()[2]){
//                setVelocity(new Vector(-500,0));
//            }else if(i.collideWith(this).getCollisionPosition()[3]){
//                setVelocity(new Vector(500,0));
//            }
//        }
    }

}
