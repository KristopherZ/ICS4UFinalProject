package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Lavaball extends CollisionBodyImage {


    private final ArrayList<Player> players = new ArrayList<>();

    private boolean isClose = false;

    /**
     * Constructs a LavaBall object
     *
     * @param x     position in x-axis
     * @param y     position in y-axis
     * @param sizeX the width of the object
     * @param sizeY the height of the object
     * @param image the image of the object
     */
    public Lavaball(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }



    public void update(long elapsedTime) {
        super.update(elapsedTime);
    }


}
