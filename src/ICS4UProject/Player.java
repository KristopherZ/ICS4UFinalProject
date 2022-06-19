package ICS4UProject;

import java.awt.*;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player extends CollisionBodyImage {

    private boolean isUpdate = true;
    private final static double coefficientOfZerothTerm = 100, coefficientOfFirstTerm = 50, exponent = 1.7;
    private final int[] elasticity = {1,1,1,1};
    private ArrayList<Body> kineticList = new ArrayList<>();
    private ArrayList<Vector> normalForceList = new ArrayList<>();
    private Vector horizontalForce = new Vector();
    private ArrayList<PlatformImage> platformList = new ArrayList<>();
    private ArrayList<PlatformImage> getPlatformList() {
        return platformList;
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

    public void setPhysics() {
        this.setGravity(new Vector(0,1000));
        this.setVelocity(new Vector(0,0));
    }

    private boolean touchingGround() {
        for(PlatformImage i : platformList) {
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
            horizontalForce.set(new Vector(-3000, 0));
        }else if(k.isdPressed()){
            horizontalForce.set(new Vector(3000,0));
        }else{
            horizontalForce.set(new Vector());
        }
        if(k.iswPressed() && touchingGround()){
            this.addAppliedForce(new Vector(0,-3000),200);
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
        }
    }

    @Override
    public void close() {
        super.close();
        isUpdate = false;
    }

}
