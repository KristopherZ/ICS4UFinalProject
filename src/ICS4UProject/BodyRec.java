package ICS4UProject;

public class BodyRec extends GameObjectRec implements Body{


    private Vector gravity = new Vector();
    private Drag drag = new Drag(this,0);
    private Vector friction  = new Vector();
    private Vector normalForce = new Vector();

    public BodyRec(double x, double y, double sizeX, double sizeY) {
        super(x, y, sizeX, sizeY);
        getForceList().add(gravity);
        getForceList().add(drag);
        getForceList().add(friction);
        getForceList().add(normalForce);
    }

    public BodyRec(Vector v, double sizeX, double sizeY) {
        this(v.getX(),v.getY(),sizeX,sizeY);
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
