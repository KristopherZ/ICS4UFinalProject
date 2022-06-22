package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * represents the simple enemy object
 * better known as the "goomba"
 */

public class Fireball extends Enemy {

    private boolean isClose = false;
    private boolean isJumping = false;

    /**
     * To construct a Fireball
     * @param x the x coordinate of the enemy
     * @param y the y coordinate of the enemy
     * @param sizeX the width
     * @param sizeY the height
     * @param image the image of the enemy
     */
    public Fireball(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
    }

    public void collide() {
        for (Player j : getPlayers()) {
            if(j.runIntoEnemyBottomOrTop(this)) {
                if(j.isPowerUp())
                    j.setIsPowerUp(false);
                else
                    j.close();
            }
        }
    }

    private void jump() {
        setIsInvisible(10000);
        if(isJumping) {
            setAppliedForce(new Vector(0,1200),200);
        }
    }

    private void setIsInvisible(long duration) {
        isJumping = true;
        (new Thread(()->{
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isJumping = false;
        })).start();
    }


    /**
     * updates collision position of the enemy
     * @param elapsedTime the time between two update is called
     */
    @Override
    public void update(long elapsedTime) {
        if(!isClose){
            super.update(elapsedTime);
            collide();
            jump();
        }
    }

    @Override
    public void close() {
        isClose = true;
        super.close();

    }
}