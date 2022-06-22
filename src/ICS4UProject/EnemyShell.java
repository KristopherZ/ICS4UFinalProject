package ICS4UProject;

import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Random;

/**
 * represents the shell enemy object
 * better known as the "koopa troopa"
 */

public class EnemyShell extends Enemy {

    private boolean isClose = false;
    private boolean shellForm = false;
    private boolean isMoving = false;


    Image shellImage = new Image((new File("Sprites/shell.png")).toURI().toURL().toString(),false);

    public void setShellImage(Image shellImage) {
        this.shellImage = shellImage;
    }

    /**
     * To construct an enemy shell
     * @param x the x coordinate of the enemy shell
     * @param y the y coordinate of the enemy shell
     * @param sizeX the width
     * @param sizeY the height
     * @param image the image of the enemy shell
     */
    public EnemyShell(double x, double y, double sizeX, double sizeY, Image image) throws MalformedURLException {
        super(x, y, sizeX, sizeY, image);
    }

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }


    /**
     * exert forces in the opposite direction of impact to make the enemy go back and forth
     * if the shell enemy is jumped on, it will turn into a shell
     * if the shell enemy is jumped on (while it is not in shell form), it will harm the player
     * if the shell enemy is jumped on, or ran into while in shell form, it will go back and forth and can harm the player
     */
    @Override
    public void collide() {
        for(PlatformImage i : getPlatformImageList()) {
            if( (i.collideWith(this).getCollisionPosition()[2]) && !shellForm){
                setVelocity(new Vector(-500,0));
            }else if( (i.collideWith(this).getCollisionPosition()[3]) && !shellForm){
                setVelocity(new Vector(500,0));
            } else if( (i.collideWith(this).getCollisionPosition()[2]) && shellForm) {
                setVelocity(new Vector(-600, 0));
            } else if( (i.collideWith(this).getCollisionPosition()[3]) && shellForm) {
                setVelocity(new Vector(600,0));
            }
        }
        for(Player i : getPlayers()) {
            if (i.jumpOnEnemy(this) && !shellForm && !i.isInvisible()) {
                this.getImage().setImage(shellImage);
                shellForm = true;
                i.setAppliedForce(new Vector(0,-12000),150);
                i.setIsInvisible(1000);
                setVelocity(new Vector());
            }
            else if( (i.runIntoEnemyRight(this) || i.runIntoEnemyLeft(this)) && !i.isInvisible() && !shellForm && !isMoving){
                if(i.isPowerUp())
                    i.setIsPowerUp(false);
                else {
                    i.gameEnd(false);
                }
                    i.gameEnd(false);
            }
            else if(i.jumpOnEnemy(this) && shellForm && isMoving && !i.isInvisible()) {
                if(i.isPowerUp())
                    i.setIsPowerUp(false);
                else {
                    i.gameEnd(false);
                }
            }
            else if(i.jumpOnEnemy(this) && shellForm && !isMoving && !i.isInvisible()) {
                if(getRandomBoolean())
                    setVelocity(new Vector(-500,0));
                else
                    setVelocity(new Vector(500, 0));
                i.setAppliedForce(new Vector(0,-12000),150);
                isMoving = true;
            }
            else if((i.runIntoEnemyRight(this) || i.runIntoEnemyLeft(this)) && !i.isInvisible() && shellForm && isMoving) {
                if(i.isPowerUp())
                    i.setIsPowerUp(false);
                else {
                    i.gameEnd(false);
                }

            }
            else if(i.runIntoEnemyLeft(this) && shellForm && !isMoving && !i.isInvisible()) {
                setVelocity(new Vector(-500,0));
                isMoving = true;
                i.setIsInvisible(1000);
            }
            else if(i.runIntoEnemyRight(this) && shellForm && !isMoving && !i.isInvisible()) {
                setVelocity(new Vector(500, 0));
                isMoving = true;
                i.setIsInvisible(1000);
            }
        }
        for(CollisionBodyImage i : getAllCollision()) {
            if(i!=this) {
                if((this.runIntoEnemyLeft(i) && !shellForm) ) {
                    setVelocity(new Vector(-100,0));
                }
                if((this.runIntoEnemyRight(i) && !shellForm) ) {
                    setVelocity(new Vector(-100,0));
                }
                else if((this.runIntoEnemyLeft(i) || this.runIntoEnemyRight(i)) && shellForm && isMoving) {
                    i.close();
                }
            }
        }
    }




    /**
     * updates the shell enemy's collision
     * @param elapsedTime the time between two update is called
     */
    @Override
    public void update(long elapsedTime) {
        if(!isClose) {
            super.update(elapsedTime);
        }
    }

    /**
     * Closes the shell enemy object by stopping it from updating
     */
    @Override
    public void close() {
        isClose = true;
        super.close();
    }

}