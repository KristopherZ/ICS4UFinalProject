package ICS4UProject;

import javafx.scene.image.Image;

public class Player extends CollisionBodyImage{

    private Image[] playerStates = new Image[5];


    public Player(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
    }

    public void setPlayerStates(Image[] arr) {
        playerStates = arr;
    }


    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        if(getVelocity().getY() < 0)
            getImage().setImage(playerStates[0]);
        else if(getVelocity().getX() > 0)
            getImage().setImage(playerStates[1]);
        else if(getVelocity().getX() < 0)
            getImage().setImage(playerStates[2]);
        else
            getImage().setImage(playerStates[3]);
    }

}