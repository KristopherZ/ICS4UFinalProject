package ICS4UProject;

import java.util.ArrayList;


/**
 *
 */
public interface Body extends KineticsWithSize{
    public Vector getGravity();
    public Vector getDrag();
    public double getDragCoe();
    public Vector getNormalForce();
    public Vector getFriction();
    public void setGravity(Vector v);
    public void setDragCoe(double dragCoe);
    public void setNormalForce(Vector v);
    public void setFriction(Vector v);
    public ArrayList<Vector> getForceList();
}
