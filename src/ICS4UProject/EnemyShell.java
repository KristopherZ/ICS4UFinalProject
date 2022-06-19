package ICS4UProject;

import javafx.scene.image.Image;

/**
 * represents the shell enemy object
 * better known as the "koopa troopa"
 */

public class EnemyShell extends Enemy {

    private boolean pickUp = false;
    public boolean hit = false;
    private boolean isUpdate = true;

    /**
     * To construct an enemy shell
     * @param x the x coordinate of the enemy shell
     * @param y the y coordinate of the enemy shell
     * @param sizeX the width
     * @param sizeY the height
     * @param image the image of the enemy shell
     */
    public EnemyShell(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
    }

    /**
     * exert forces in the opposite direction of impact to make the enemy go back and forth
     * if the shell enemy is jumped on, it will turn into a shell
     * if the shell enemy is jumped on (while it is not in shell form), it will harm the player
     * if the shell enemy is jumped on, or ran into while in shell form, it will go back and forth and can harm the player
     */
    private void collide() {
        for(PlatformImage i : getPlatformImageList()) {
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

    /**
     * updates the shell enemy's collision
     * @param elapsedTime the time between two update is called
     */
    @Override
    public void update(long elapsedTime) {
        if(isUpdate) {
            super.update(elapsedTime);
            collide();
        }
    }

    /**
     * Closes the shell enemy object by stopping it from updating
     */
    public void close() {
        super.close();
        isUpdate = false;
    }

}
