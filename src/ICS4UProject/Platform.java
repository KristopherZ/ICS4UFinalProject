package ICS4UProject;

import java.util.ArrayList;
import java.util.Arrays;

public class Platform extends CollisionRec {
    private final static double coefficientOfZerothTerm = 100, coefficientOfFirstTerm = 50, exponent= 1.7;
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
            System.out.println(Arrays.toString(e.getDepth()));
            double min = Double.POSITIVE_INFINITY;
            int minIndex = -1;
            for(int j=0;j<4;j++) {
                if(e.getDepth()[j] < min) {
                    min=e.getDepth()[j];
                    minIndex=j;
                }
            }
            System.out.println(minIndex);
            switch (minIndex){
                case 0: normalForceList.get(i).set(
                        new Vector(0,
                                (-Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                        -kineticList.get(i).getVelocity().getY()*coefficientOfFirstTerm)*kineticList.get(i).getMass()));
                break;
                case 1: normalForceList.get(i).set(
                        new Vector(0,
                                (Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                        -kineticList.get(i).getVelocity().getY()*coefficientOfFirstTerm)*kineticList.get(i).getMass()));
                break;
                case 2: normalForceList.get(i).set(
                        new Vector((-Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                -kineticList.get(i).getVelocity().getX()*coefficientOfFirstTerm)*kineticList.get(i).getMass(),0));
                break;
                case 3: normalForceList.get(i).set(
                        new Vector((Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                -kineticList.get(i).getVelocity().getX()*coefficientOfFirstTerm)*kineticList.get(i).getMass(),0));
                break;
            }
        }

    }

    @Override
    public void update(long elapsedTime){
        super.update(elapsedTime);
        collide();
    }
}
