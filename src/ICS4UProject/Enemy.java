package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Enemy extends CollisionBodyImage {

    private final static double coefficientOfZerothTerm = 100, coefficientOfFirstTerm = 50, exponent= 1.7;

    private ArrayList<Platform> platformList = new ArrayList<>();
    private Player player;

    public Enemy(double x, double y, double sizeX, double sizeY, Image image, Player player) {
        super(x, y, sizeX, sizeY, image);
        setElasticity(new double[]{0,1,0,0});
        this.player = player;
    }

    public ArrayList<Platform> getPlatformList() {
        return platformList;
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        for(Platform i : platformList) {
            if(i.collideWith(this).getCollisionPosition()[2]){
                setVelocity(new Vector(-500,0));
            }else if(i.collideWith(this).getCollisionPosition()[3]){
                setVelocity(new Vector(500,0));
            }
        }
        if(this.collideWith(player).getCollisionPosition()[0]){

        }
    }

}