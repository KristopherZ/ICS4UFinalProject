package ICS4UProject;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * represents the simple enemy object
 * better known as the "goomba"
 */

public class Enemy extends CollisionBodyImage {

    private final static double coefficientOfZerothTerm = 100, coefficientOfFirstTerm = 50, exponent= 1.7;
    private final ArrayList<Player> players = new ArrayList<>();
    private final ArrayList<PlatformImage> platformImageList = new ArrayList<>();
    private boolean isClose = false;

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
        setElasticity(new double[]{0,1,0,0});
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
     * closes the game if player touches the enemy, and it is not powered up
     * closes the enemy if it is jumped on
     * gets rid of players power up if ran into with a power up on
     */
    public void collide() {
        //check if the enemy collide
        for (PlatformImage i : platformImageList) {
            if (i.collideWith(this).getCollisionPosition()[2]) {
                setVelocity(new Vector(-200,0));
            } else if (i.collideWith(this).getCollisionPosition()[3]) {
                setVelocity(new Vector(200,0));
            }
        }
        for (Player j : players) {
            if (j.jumpOnEnemy(this)&& !j.isInvisible()) {
                AudioClip music = null;
                try {
                    music = new AudioClip((new File("Sounds\\Goomba.mp3")).toURI().toURL().toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                music.setCycleCount(1);
                music.play();
                this.close();
                j.addScore(100);
            }
            else if (j.runIntoEnemy(this)) {
                if(j.isPowerUp())
                    j.setIsPowerUp(false);
                else
                    j.gameEnd(false);
            }
        }
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
        }
    }

    @Override
    public void close() {
        isClose = true;
        super.close();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}