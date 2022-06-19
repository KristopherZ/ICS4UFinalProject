package ICS4UProject;


import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * This class represents a mushroom so that the player can be powered up
 */
public class Mushroom extends CollisionBodyImage{

    private PlatformImage trigger;
    private boolean isTriggered = false;
    private ArrayList<Player> playerArrayList = new ArrayList<>();
    private Vector TempGravity,movingVelocity;
    private Boolean isEaten = false;


    /**
     * Constructs a Mushroom
     * @param trigger the trigger platform
     * @param sizeX the width of the object
     * @param sizeY the height of the object
     * @param image the image of the object
     */

    public Mushroom(PlatformImage trigger, double sizeX, double sizeY, Image image) {
        super(trigger.getPosition().getX(), trigger.getPosition().getY()-1000, sizeX, sizeY, image);
        this.trigger = trigger;
        setElasticity(new double[]{1,1,1,1});
    }

    /**
     * Add players to the mushroom class so that it can be triggered
     * @param p the player
     */

    public void addPlayer(Player p){
        playerArrayList.add(p);
    }

    /**
     * Helper method to check if the trigger is activate
     * @return the whether the trigger is activate
     */
    private boolean checkTrigger(){
//        System.out.println(isTriggered);
        if(!isTriggered){
            for (Player p :
                    playerArrayList) {
                isTriggered = trigger.collideWith(p).getCollisionPosition()[1];
                return trigger.collideWith(p).getCollisionPosition()[1];
            }
        }
        return false;
    }


    @Override
    public void setGravity(Vector v) {
        TempGravity = v;
    }

    /**
     * set the moving velocity of the mushroom
     * @param v the moving velocity of the mushroom
     */

    public void setMovingVelocity(Vector v){
        movingVelocity = v;
    }

    @Override
    public void update(long elapsedTime) {
        super.update(elapsedTime);
        if(!isTriggered)
            setPosition(new Vector(trigger.getPosition().getX(), trigger.getPosition().getY()-1000));
        if(checkTrigger()){
            setPosition(getPosition().add(new Vector(0,1000- getSizeY())));
            setVelocity(movingVelocity);
            super.setGravity(TempGravity);
        }
        for(Player p:playerArrayList){
            if(isCollide(p.getImage())&&!isEaten){
                this.close();
                isEaten = true;
                System.out.println("m");//need to be replaced by power up method in the player class
            }
        }
    }
}
