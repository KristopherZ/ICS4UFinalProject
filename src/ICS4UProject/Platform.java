package ICS4UProject;

import java.util.ArrayList;
import java.util.Collections;

public class Platform extends CollisionRec {

    private int minCollide = Integer.MAX_VALUE;
    ArrayList<KineticsWithSize> kineticList = new ArrayList<>();
    ArrayList<Vector> normalForceList = new ArrayList<>();

    public Platform(double x, double y, double sizeX, double sizeY) {
        super(x, y, sizeX, sizeY);
    }



    public void addKinetic(KineticsWithSize k) {
        kineticList.add(k);
        Vector normalForce = new Vector();
        normalForceList.add(normalForce);
        k.getForceList().add(normalForce);
    }


    public void collide(){
        for(int i=0;i<kineticList.size();i++){
            CollisionEvent e = collideWith(kineticList.get(i));
            }
        }
    }
