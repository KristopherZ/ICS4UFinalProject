package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * represents the main player object
 */

public class Player extends CollisionBodyImage {

    private final Vector HORIZONTAL_FORCE = new Vector();
    private final ArrayList<PlatformImage> PLATFORM_IMAGE_LIST = new ArrayList<>();
    private final KeyInput k;
    public ArrayList<PlatformImage> getPlatformImageList() {
        return PLATFORM_IMAGE_LIST;
    }
    private Image[] playerStates = new Image[5];
    private boolean isUpdate = true;

    /**
     * To construct a player
     * @param x the x coordinate of the player
     * @param y the y coordinate of the player
     * @param sizeX the width
     * @param sizeY the height
     * @param image the image of the platform
     * @param k the key detection
     */
    public Player(double x, double y, double sizeX, double sizeY, Image image, KeyInput k) {
        super(x, y, sizeX, sizeY, image);
        getForceList().add(HORIZONTAL_FORCE);
        setElasticity(new double[]{1,1,1,1});
        playerStates = new Image[]{image,image,image,image,image};
        this.k = k;
    }

    /**
     * Checks if the player is on the ground
     * Useful to avoid the player from "flying" and jumping while in the air
     * @return if the player is touching the ground
     */
    private boolean touchingGround() {
        for(PlatformImage i : PLATFORM_IMAGE_LIST) {
            if(i.collideWith(this).getCollisionPosition()[0]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player has jumped on the enemy
     * @param enemy current enemy
     * @return if the player is jumping on the enemy
     */
    public boolean jumpOnEnemy(Enemy enemy) {
        return this.collideWith(enemy).getCollisionPosition()[1];
    }

//    public boolean killPlayer(Enemy enemy) {
//        return this.collideWith(enemy).getCollisionPosition()[2] || this.collideWith(enemy).getCollisionPosition()[3];
//    }

    /**
     * exert a forces relative to the respective key press for player movement
     * w - up
     * a - left
     * d - right
     */
    private void keyMovement() {
        if(k.isaPressed()&&k.isdPressed()){
            HORIZONTAL_FORCE.set(new Vector());
        }else if(k.isaPressed()) {
            if(touchingGround())
                HORIZONTAL_FORCE.set(new Vector(-4000, 0));
            else
                HORIZONTAL_FORCE.set(new Vector(-600, 0));
        }else if(k.isdPressed()){
            if(touchingGround())
                HORIZONTAL_FORCE.set(new Vector(4000, 0));
            else
                HORIZONTAL_FORCE.set(new Vector(600, 0));
        }else{
            HORIZONTAL_FORCE.set(new Vector());
        }
        if(k.iswPressed() && touchingGround()){
            this.setAppliedForce(new Vector(0,-11000),150);
        }
    }

    /**
     * Sets the player image depending on current movement, to simulate animation
     */
    private void playerStateChange() {
        if(getVelocity().getY() < 0)
            getImage().setImage(playerStates[0]);
        else if(getVelocity().getX() > 0)
            getImage().setImage(playerStates[1]);
        else if(getVelocity().getX() < 0)
            getImage().setImage(playerStates[2]);
        else
            getImage().setImage(playerStates[3]);
    }

    /**
     * includes a horizontal velocity cap to avoid player acceleration beyond a certain point
     * @param elapsedTime the time between two update is called
     */
    @Override
    public void update(long elapsedTime) {
        if(isUpdate){
            super.update(elapsedTime);
            keyMovement();
            playerStateChange();
            if(getVelocity().getX() > 500)
                setVelocity(new Vector(500, getVelocity().getY()));
            if(getVelocity().getX() < -500) {
                setVelocity(new Vector(-500,getVelocity().getY()));
            }
        }
    }

    /**
     * Closes the player object by stopping it from updating
     */
    @Override
    public void close() {
        super.close();
        isUpdate = false;
    }

    /**
     * This is used to set the display image for different states of the player
     * @param playerStates a 5-element Image Array
     */
    public void setPlayerStates(Image[] playerStates) {
        this.playerStates = playerStates;
    }
}