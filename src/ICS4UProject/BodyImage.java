package ICS4UProject;

import javafx.scene.image.Image;

/**
 * This is a class represent a body with image added
 */
public class BodyImage extends GameObjectImage implements Body{


    private Vector gravity = new Vector();
    private Drag drag = new Drag(this,0);
    private Vector friction  = new Vector();
    private Vector normalForce = new Vector();

    /**
     * initialize the body
     * @param x the x coordinate of the object
     * @param y the y coordinate of the object
     * @param sizeX the width of the object
     * @param sizeY the height of the object
     * @param image the image of the object
     */
    public BodyImage(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
        getForceList().add(gravity);
        getForceList().add(drag);
        getForceList().add(friction);
        getForceList().add(normalForce);
    }

    /**
     *
     * @param v the position of the oject
     * @param sizeX the width of the object
     * @param sizeY the height of the object
     * @param image the image of the object
     */
    public BodyImage(Vector v, double sizeX, double sizeY, Image image) {
        super(v, sizeX, sizeY, image);
        getForceList().add(gravity);
        getForceList().add(drag);
        getForceList().add(friction);
        getForceList().add(normalForce);
    }

    /**
     * To get the gravity of the object
     * @return the gravity of the object
     */
    @Override
    public Vector getGravity() {
        return gravity;
    }

    /**
     * To get the current drag force
     * @return the current drag force
     */
    @Override
    public Vector getDrag() {
        return drag.getCurrentValue();
    }

    /**
     *
     * @return the drag coefficient of air drag
     */
    @Override
    public double getDragCoe() {
        return drag.getDragCoe();
    }

    /**
     *
     * @return the default normal force of the object
     */
    @Override
    public Vector getNormalForce() {
        return normalForce;
    }

    /**
     *
     * @return the default friction
     */
    @Override
    public Vector getFriction() {
        return friction;
    }

    /**
     * To est the gravity
     * @param v the gravitational force
     */
    @Override
    public void setGravity(Vector v) {
        gravity.set(v);
    }

    /**
     * to set the drag coefficient
     * @param dragCoe
     */
    @Override
    public void setDragCoe(double dragCoe) {
        drag.setDragCoe(dragCoe);
    }

    /**
     * to set the normal force
     * @param v the normal force
     */
    @Override
    public void setNormalForce(Vector v) {
        normalForce.set(v);
    }

    /**
     * to set the friction
     * @param v the friction force
     */
    @Override
    public void setFriction(Vector v) {
        friction.set(v);
    }
}
