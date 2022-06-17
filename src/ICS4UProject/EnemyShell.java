package ICS4UProject;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class EnemyShell extends Enemy {

    private boolean pickUp = false;
    public boolean hit = false;
    private boolean isUpdate = true;


    public EnemyShell(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
    }

    @Override
    public void update(long elapsedTime) {
        if(isUpdate) {
            super.update(elapsedTime);
            for(Platform i : getPlatformList()) {
                if( (i.collideWith(this).getCollisionPosition()[2]) && !pickUp){
                    setVelocity(new Vector(-500,0));
                    hit = true;
                }else if( (i.collideWith(this).getCollisionPosition()[3]) && !pickUp){
                    setVelocity(new Vector(500,0));
                    hit = true;
                } else if( (i.collideWith(this).getCollisionPosition()[1]) && !pickUp) {
                    setVelocity(new Vector(0,0));
                    pickUp = true;
                } else if ( (i.collideWith(this).getCollisionPosition()[2]) && pickUp) {
                    setVelocity(new Vector(-500,0));
                } else if( (i.collideWith(this).getCollisionPosition()[3]) && pickUp){
                    setVelocity(new Vector(500,0));
                }
            }

        }
    }

    public void close() {
        super.close();
        isUpdate = false;
    }

}
