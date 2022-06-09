package ICS4UProject;

import java.util.ArrayList;

/**
 * Body interface
 * Provides appropriate getting and setter methods for key player components
 */

public interface Body extends KineticsWithSize{
    /**
     * Getter and setter for gravity, drag, drag coefficient normal friction and friction
     */
    public Vector getGravity();
    public void setGravity(Vector v);
    public Vector getDrag();
    public void setDragCoe(double dragCoe);
    public double getDragCoe();
    public Vector getNormalForce();
    public void setNormalForce(Vector v);
    public Vector getFriction();
    public void setFriction(Vector v);
}
