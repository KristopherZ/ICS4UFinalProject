package ICS4UProject;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;

public class PlatformImage extends CollisionImage{
    private final static double coefficientOfZerothTerm = 100, coefficientOfFirstTerm = 50, exponent= 1.7;
    private ArrayList<Body> kineticList = new ArrayList<>();
    private ArrayList<Vector> normalForceList = new ArrayList<>();

    public PlatformImage(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
    }

    public void addKinetic(Body k) {
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
                if(e.getDepth()[j] < min && e.getDepth()[j]>0) {
                    min=e.getDepth()[j];
                    minIndex=j;
                }
            }
            System.out.println(minIndex);
            switch (minIndex){
                case 0: normalForceList.get(i).set(
                        new Vector((Math.abs(kineticList.get(i).getVelocity().getX())<10 ? -getFrictionCoe()*kineticList.get(i).getVelocity().getX()*10:
                                (kineticList.get(i).getVelocity().getX()>0 ? -1:1)*getFrictionCoe()*kineticList.get(i).getGravity().length())*kineticList.get(i).getFrictionCoe(),
                                (-Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                        -kineticList.get(i).getElasticity()[1]*kineticList.get(i).getVelocity().getY()*coefficientOfFirstTerm)*kineticList.get(i).getMass()));
                    break;
                case 1: normalForceList.get(i).set(
                        new Vector(0,
                                (Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                        -kineticList.get(i).getElasticity()[0]*kineticList.get(i).getVelocity().getY()*coefficientOfFirstTerm)*kineticList.get(i).getMass()));
                    break;
                case 2: normalForceList.get(i).set(
                        new Vector((-Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                -kineticList.get(i).getElasticity()[3]*kineticList.get(i).getVelocity().getX()*coefficientOfFirstTerm)*kineticList.get(i).getMass(),
                                0));
                    break;
                case 3: normalForceList.get(i).set(
                        new Vector((Math.pow(e.getDepth()[minIndex],exponent)*coefficientOfZerothTerm
                                -kineticList.get(i).getElasticity()[2]*kineticList.get(i).getVelocity().getX()*coefficientOfFirstTerm)*kineticList.get(i).getMass(),
                                0));
                    break;
                default:
                    normalForceList.get(i).set(new Vector());
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
