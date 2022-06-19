package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * represents the simple enemy object
 * better known as the "goomba"
 */

public class Enemy extends CollisionBodyImage {

    private final static double coefficientOfZerothTerm = 100, coefficientOfFirstTerm = 50, exponent= 1.7;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<PlatformImage> platformImageList = new ArrayList<>();

    /**
     * To construct a player
     * @param x the x coordinate of the enemy
     * @param y the y coordinate of the enemy
     * @param sizeX the width
     * @param sizeY the height
     * @param image the image of the enemy
     */
    public Enemy(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
        setElasticity(new double[]{1,1,1,1});
    }

    /**
     * To get the current platformImage list
     * @return the current platformImage list
     */
    public ArrayList<PlatformImage> getPlatformImageList() {
        return platformImageList;
    }

    /**
     * To add to the list of players
     * @param player the player
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * exert forces in the opposite direction of impact to make the enemy go back and forth
     * closes the player if it touches the enemy
     */
    private void collide() {
        for(PlatformImage i : platformImageList) {
            if(i.collideWith(this).getCollisionPosition()[2]){
                setVelocity(new Vector(-100,0));
            }else if(i.collideWith(this).getCollisionPosition()[3]){
                setVelocity(new Vector(100,0));
            }
        }
        for(Player j : players) {
            if(j.jumpOnEnemy(this)) {
                this.close();
            }
        }
    }

    /**
     * updates collision position of the enemy
     * @param elapsedTime the time between two update is called
     */
    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        for(Player i : players) {
            this.collideWith(i);
        }
        collide();
    }

}
