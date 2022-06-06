package ICS4UProject;

import javafx.scene.image.Image;

public class BodyImage extends GameObjectImage implements Body{


    private Vector gravity = new Vector();
    private Drag drag = new Drag(this,0);
    private Vector friction  = new Vector();
    private Vector normalForce = new Vector();

    public BodyImage(double x, double y, double sizeX, double sizeY, Image image) {
        super(x, y, sizeX, sizeY, image);
        getForceList().add(gravity);
        getForceList().add(drag);
        getForceList().add(friction);
        getForceList().add(normalForce);
    }

    public BodyImage(Vector v, double sizeX, double sizeY, Image image) {
        super(v, sizeX, sizeY, image);
        getForceList().add(gravity);
        getForceList().add(drag);
        getForceList().add(friction);
        getForceList().add(normalForce);
    }


    @Override
    public Vector getGravity() {
        return gravity;
    }

    @Override
    public Vector getDrag() {
        return drag.getCurrentValue();
    }

    @Override
    public double getDragCoe() {
        return drag.getDragCoe();
    }

    @Override
    public Vector getNormalForce() {
        return normalForce;
    }

    @Override
    public Vector getFriction() {
        return friction;
    }

    @Override
    public void setGravity(Vector v) {
        gravity.set(v.multiply(getMass()));
    }

    @Override
    public void setDragCoe(double dragCoe) {
        drag.setDragCoe(dragCoe);
    }

    @Override
    public void setNormalForce(Vector v) {
        normalForce.set(v);
    }

    @Override
    public void setFriction(Vector v) {
        friction.set(v);
    }
}
