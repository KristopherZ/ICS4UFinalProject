package ICS4UProject;

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
     * @return the drag coefficient of the object
     */
    public double getDragCoe();

    /**
     *
     * @return the default normal force of the object
     */
    public Vector getNormalForce();

    /**
     *
     * @return the default friction of the object
     */
    public Vector getFriction();

    /**
     * set the gravity (a constant force) to the vector in the parameter
     * @param v the gravity vector
     */
    public void setGravity(Vector v);

    /**
     * set the drag coefficient so that the drag is calculated by |D| = C*|v|^2 and opposite to the direct of motion
     * @param dragCoe the drag coefficient
     */
    public void setDragCoe(double dragCoe);

    /**
     * set the normal force to v
     * @param v the default normal force of the object
     */
    public void setNormalForce(Vector v);

    /**
     * set the friction to v
     * @param v the default friction force of the object
     */
    public void setFriction(Vector v);
}
