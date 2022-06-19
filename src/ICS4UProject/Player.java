package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Player extends CollisionBodyImage {

    private boolean isUpdate = true;

    private Vector horizontalForce = new Vector();

    private ArrayList<PlatformImage> PlatformImageList = new ArrayList<>();

    public ArrayList<PlatformImage> getPlatformImageList() {
        return PlatformImageList;
    }
    private Image[] playerStates = new Image[5];

    KeyInput k;

    public Player(double x, double y, double sizeX, double sizeY, Image image, KeyInput k) {
        super(x, y, sizeX, sizeY, image);
        getForceList().add(horizontalForce);
        setElasticity(new double[]{1,1,1,1});
        playerStates = new Image[]{image,image,image,image,image};
        this.k = k;
    }

    private boolean touchingGround() {
        for(PlatformImage i : PlatformImageList) {
            if(i.collideWith(this).getCollisionPosition()[0]) {
                return true;
            }
        }
        return false;
    }


    private void keyMovement() {
        if(k.isaPressed()&&k.isdPressed()){
            horizontalForce.set(new Vector());
        }else if(k.isaPressed()) {
            if(touchingGround())
                horizontalForce.set(new Vector(-4000, 0));
            else
                horizontalForce.set(new Vector(-600, 0));
        }else if(k.isdPressed()){
            if(touchingGround())
                horizontalForce.set(new Vector(4000, 0));
            else
                horizontalForce.set(new Vector(600, 0));
        }else{
            horizontalForce.set(new Vector());
        }
        if(k.iswPressed() && touchingGround()){
            this.setAppliedForce(new Vector(0,-11000),150);
        }
    }

    @Override
    public void update(long elapsedTime) {
        if(isUpdate){
            super.update(elapsedTime);
            keyMovement();
            if(getVelocity().getY() < 0)
                getImage().setImage(playerStates[0]);
            else if(getVelocity().getX() > 0)
                getImage().setImage(playerStates[1]);
            else if(getVelocity().getX() < 0)
                getImage().setImage(playerStates[2]);
            else
                getImage().setImage(playerStates[3]);
            if(getVelocity().getX() > 500) {
                setVelocity(new Vector(500, getVelocity().getY()));
            }
            if(getVelocity().getX() < -500) {
                setVelocity(new Vector(-500, getVelocity().getY()));
            }
        }
    }

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