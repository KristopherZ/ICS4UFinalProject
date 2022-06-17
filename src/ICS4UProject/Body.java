package ICS4UProject;

import java.util.ArrayList;


/**
 * This body interface represents a game object with dimension and basic forces
 */
public interface Body extends KineticsWithSize{
    /**
     *
     * @return get the gravity of the object
     */
    public Vector getGravity();

    /**
     *
     * @return get the drag coefficient of the drag
     */
    public Vector getDrag();

    /**
     *
     * @return
     */
    public double getDragCoe();
    public Vector getNormalForce();
    public Vector getFriction();
    public void setGravity(Vector v);
    public void setDragCoe(double dragCoe);
    public void setNormalForce(Vector v);
    public void setFriction(Vector v);
}
